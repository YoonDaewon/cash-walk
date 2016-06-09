using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{
    private static AndroidJavaObject cur_activity;

    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
           // AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.example.yoon.lib.StartActivity");
            //cur_activity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
            //cur_activity.CallStatic("showAndroidView");
            UnityPlayer.CallStatic("showAndroidView");
        }
    }
}