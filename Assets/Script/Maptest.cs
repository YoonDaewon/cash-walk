using UnityEngine;

using System.Collections;

public class GoogleMaps : MonoBehaviour
{

    string url = "";
    float lat;
    float lon;
    LocationInfo myLocation;
    WWW www;
    // Use this for initialization
    void Start()
    {
        myLocation = new LocationInfo();
        lat = myLocation.latitude;
        lon = myLocation.longitude;

        url = "http://maps.google.com/maps/api/staticmap?center=" + lat + "," + lon + "&zoom=14&size=800x600&maptype=hybrid&sensor=true";

        StartCoroutine(Myyield(url));
        Debug.Log("lat : " + lat + " lon : " + lon);
    }

    IEnumerator Myyield(string url)
    {
        this.www = new WWW(url);

        while (!www.isDone)
        {
            yield return new WaitForSeconds(1);
        }
        GetComponent<Renderer>().material.mainTexture = www.texture;
    }


    // Update is called once per frame

    void Update()
    {

    }

}
