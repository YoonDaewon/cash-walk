using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{
    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
            AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaObject cur_activity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
            AndroidJavaClass AndroidPlayer = new AndroidJavaClass("com.example.yoon.lib.StartActivity");
            //AndroidPlayer.CallStatic("showAndroidView");
            AndroidPlayer.CallStatic("Call", cur_activity);
        }
    }
}