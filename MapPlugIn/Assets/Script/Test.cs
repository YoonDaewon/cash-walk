using UnityEngine;
using System.Collections;

public class Test : MonoBehaviour {
    private AndroidJavaClass bridge;
    private string imei;

    // Use this for initialization
    void Start () {
        

        Debug.Log("Unity before blue shareText callShareApp");
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        AndroidJavaObject app = activity.Call<AndroidJavaObject>("getApplicationContext");

        imei = SystemInfo.deviceUniqueIdentifier;

        bridge = new AndroidJavaClass("com.example.mapdemo.MainActivity");
        activity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
         {
             bridge.CallStatic("callMap", app, imei);
         }));

        Debug.Log("Unity What is the name of gameobjects?" + gameObject.name);
        Debug.Log("Unity after blue callShareApp");


	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
