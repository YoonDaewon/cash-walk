using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{
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
        if (Application.platform == RuntimePlatform.Android)
        {
            if (GUI.Button(new Rect(0, 0, 200, 200), "Android Screen"))
            {
                AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
                AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
                activity.Call("showAndroidView");

            }
        }
        else
        {
            if (GUI.Button(new Rect(0, 0, 200, 200), "Fail"))
            {

            }
        }
    }
}