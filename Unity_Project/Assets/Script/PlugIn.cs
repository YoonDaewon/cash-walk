using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{
<<<<<<< HEAD


=======
    private static AndroidJavaObject cur_activity;
>>>>>>> 62fd1ad8d6a9345c0aa3005adc4ed2d72a4ff450

    void OnGUI()
    {
        if (GUI.Button(new Rect(0, 0, 200, 200), "Login"))
        {
<<<<<<< HEAD
            // to get the activity
            var androidJC = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            var jo = androidJC.GetStatic<AndroidJavaObject>("currentActivity");
            // Accessing the class to call a static method on it
            var jc = new AndroidJavaClass("com.example.fboomplug.StartActivity");
            // Calling a Call method to which the current activity is passed
            jc.CallStatic("Call", jo);
=======
           // AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.example.yoon.lib.StartActivity");
            //cur_activity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
            //cur_activity.CallStatic("showAndroidView");
            UnityPlayer.CallStatic("showAndroidView");
>>>>>>> 62fd1ad8d6a9345c0aa3005adc4ed2d72a4ff450
        }
    }
}