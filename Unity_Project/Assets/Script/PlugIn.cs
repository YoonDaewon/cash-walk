using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{


    public static AndroidJavaClass ViewJavaClass;



    // Use this for initialization
    void Start()
    {
        if (Application.platform == RuntimePlatform.Android)
        {
            // Initialize Android View
            ViewJavaClass = new AndroidJavaClass("my.android.game.ViewActivityUnity");

            //AndroidJavaClass mapsclass = new AndroidJavaClass("com.google.android.gms.maps.MapFragment");
            //mapsclass.CallStatic("newInstance");
        }

    }

    // Update is called once per frame
    void Update()
    {

    }

    void OnGUI()
    {

        if (Application.platform == RuntimePlatform.Android)
        {
            if (GUI.Button(new Rect(10, 300, 150, 120), "Show android Screen"))
            {
                AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
                AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
                activity.Call("showAndroidView");


                //ViewJavaClass.CallStatic("showAndroidView");
            }
        }
        else
        {
            if (GUI.Button(new Rect(10, 300, 150, 120), "Show non-android Screen"))
            {

            }
        }
    }

    /*
    
    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
            // to get the activity
            var androidJC = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            var jo = androidJC.GetStatic<AndroidJavaObject>("currentActivity");
            // Accessing the class to call a static method on it
            var jc = new AndroidJavaClass("com.example.yoon.lib.StartActivity");
            // Calling a Call method to which the current activity is passed
            jc.CallStatic("Call", jo);

            
        }
        if (GUI.Button(new Rect(600, 600, 200, 200), "Login"))
    {
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        AndroidJavaObject app = activity.Call<AndroidJavaObject>("getApplicationContext");

        imei = SystemInfo.deviceUniqueIdentifier;

        bg = new AndroidJavaClass("com.example.yoon.lib.StartActivity");

        activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
        {
            bg.CallStatic("callMap", app, imei);

        }));
    }
    

    /*
    void Start()
    {
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        AndroidJavaObject app = activity.Call<AndroidJavaObject>("getApplicationContext");

        imei = SystemInfo.deviceUniqueIdentifier;

        bg = new AndroidJavaClass("com.example.yoon.lib.MainActivity");

        activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
        {
            bg.CallStatic("callMap", app, imei);

        }));
    

    
    }*/


    public string imei { get; set; }

    public AndroidJavaClass bg { get; set; }
}