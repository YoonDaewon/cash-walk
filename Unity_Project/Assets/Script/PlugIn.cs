﻿using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour
{
    void Start()
    {
        // to get the activity
        AndroidJavaClass androidJC = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject jo = androidJC.GetStatic<AndroidJavaObject>("currentActivity");
        // Accessing the class to call a static method on it
        AndroidJavaClass jc = new AndroidJavaClass("com.example.yoon.lib.MainActivity");
        // Calling a Call method to which the current activity is passed
        jc.CallStatic("Call", jo);
    }
}