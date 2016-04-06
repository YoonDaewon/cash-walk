using UnityEngine;
using System.Collections;
using System;
using System.Text;
using System.Collections.Generic;

public class LinkAndroid : MonoBehaviour {

    private AndroidJavaObject _plugin;
    private string _strText;

    void Awake()
    {
        AndroidJavaClass Ajc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        _plugin = Ajc.GetStatic<AndroidJavaObject>("currentActivity");
    }
    void Start()
    {
        _plugin.Call("ToastMessage", "PluginTexTkk");
    }
    void Update()
    {
        _plugin.Call("GetString");
    }
    public void ReceiveString(string str)
    {
        _strText = str;
    }
    void OnGUI()
    {
        GUI.Label(new Rect(0, 0, 300, 300), _strText);
    }
}
