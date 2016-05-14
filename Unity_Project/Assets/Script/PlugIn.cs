using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour {
    private  AndroidJavaClass bridge;
    private string imei;

    // Use this for initialization
    void Start()
    {
        Debug.Log("Unity before blue share Text callShareApp");
        // 안드로이드 Context를 가져옴
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        AndroidJavaObject app = activity.Call<AndroidJavaObject>("getApplicationContext");

        imei = SystemInfo.deviceUniqueIdentifier;

        // install
        bridge = new AndroidJavaClass("com.example.yoon.newgps.MainActivity");
        activity.Call("runOnUIThread", new AndroidJavaRunnable(() =>
        {
            bridge.CallStatic("callMap", app, imei);
        }));

        Debug.Log("Unity What is the name of gameobject?" + gameObject.name);
        Debug.Log("Unity after blue callShareApp");
    }

	
	// Update is called once per frame
	void Update () {
	
	}
}
