using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{


/*

    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
            // to get the activity
            AndroidJavaClass androidJC = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
          
            AndroidJavaObject jo = androidJC.GetStatic<AndroidJavaObject>("currentActivity");

            // Accessing the class to call a static method on it
            AndroidJavaClass jc = new AndroidJavaClass("com.example.yoon.lib.StartActivity");

            // Calling a Call method to which the current activity is passed
            jc.CallStatic("Call", jo);

        }
        
    } */


    public static AndroidJavaClass ViewJavaClass;



    void Start()
    {
        if (Application.platform == RuntimePlatform.Android)
        {
            ViewJavaClass = new AndroidJavaClass("com.example.yoon.lib.StartActivity");
        }
    }

    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
            AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
            activity.Call("showAndroidView");

        }

    }
}