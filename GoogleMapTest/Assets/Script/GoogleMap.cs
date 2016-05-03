using UnityEngine;
using System.Collections;

public class GoogleMap : MonoBehaviour {

	// Use this for initialization

    public enum MapType
    {
        RoadMap,
        Satellite,
        Terrain,
        Hybrid
    }
    public bool LoadOnStart = true;
    public bool autoLocateCenter = true;
    public GoogleMapLocation centerLocation;
    public int zoom = 13;
    public MapType mapType;
    public int size = 512;
    public bool doubleResolution = false;
    public GoogleMapMarker[] markers;
    public GoogleMapPath[] paths;

    


	void Start () {
        if (LoadOnStart) Refresh();
	}

    public void Refresh()
    {
        if (autoLocateCenter && (markers.Length == 0 && paths.Length == 0))
        {
            Debug.LogError("Auto Center will only work if paths or markers are used.");
        };
        StartCoroutine(_Refresh());
    }

    IEnumerator _Refresh()
    {
        var url = "http://maps.googleapis.com/maps/api/staticmap";
        var qs = "";
        if (!autoLocateCenter)
        {
            if (centerLocation.address != "")
                qs += "center=" + WWW.UnEscapeURL(centerLocation.address);
            else
            {
                qs += "&zoom=" + zoom.ToString();
            }

            qs += "&size=" + WWW.UnEscapeURL(string.Format("{0}x{0}", size));
            qs += "&scale=" + (doubleResolution ? "2" : "1");
            qs += "&maptype=" + mapType.ToString().ToLower();
            var usingSensor = false;



#if UNITY_IPHONE
            usingSensor = Input.location.isEnabledByUser && Input.location.status == LocationServiceStatus.Running;
#endif
            qs += "&sensor=" + (usingSensor ? "ture" : "false");

            foreach (var i in markers)
            {
                qs += "&markers=" + string.Format("size:{0}|color:{1}|label:{2}", i.size.ToString().ToLower(), i.color, i.Label);
                foreach (var loc in i.Locations)
                {
                    if (loc.address != "")
                        qs += "|" + WWW.UnEscapeURL(loc.address);
                    else
                        qs += "|" + WWW.UnEscapeURL(string.Format("{0},{1}", loc.Latitude, loc.Longitude));
                }
            }
            foreach (var i in paths)
            {
                qs += "&path=" + string.Format("weight :{0}|color:{1}", i.weight, i.color);
                if (i.fill) qs += "|fillcolor:" + i.fillColor;
                foreach (var loc in i.Locations)
                {
                    if (loc.address != "")
                        qs += "|" + WWW.UnEscapeURL(loc.address);
                    else
                        qs += "|" + WWW.UnEscapeURL(string.Format("{0},{1}", loc.Latitude, loc.Longitude));
                }
            }

            var req = new WWW(url + "?" + qs);
            yield return req;
            GetComponent<Renderer>().material.mainTexture = req.texture;
        }
    }
    public enum GoogleMapColor
    {
        black,
        brown,
        green,
        purple,
        yellow,
        blue,
        gray,
        orange,
        red,
        white
    }
    [System.Serializable]
    public class GoogleMapLocation
    {
        public string address;
        public float Latitude;
        public float Longitude;

    }

    [System.Serializable]
    public class GoogleMapMarker
    {
        public enum GoogleMapMarkerSize
        {
            Tiny,
            Small,
            Mid
        }
        public GoogleMapMarkerSize size;
        public GoogleMapColor color;
        public string Label;
        public GoogleMapLocation[] Locations;
    }
    [System.Serializable]
    public class GoogleMapPath
    {
        public int weight = 5;
        public GoogleMapColor color;
        public bool fill = false;
        public GoogleMapColor fillColor;
        public GoogleMapLocation[] Locations;

    }
}
