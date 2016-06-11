using UnityEngine;
using System.Collections;

public class Plugin : MonoBehaviour {

	// Use this for initialization
	public void OnclickButton()
    {
        using (AndroidJavaClass jc = new AndroidJavaClass("com.example.yoon.newgps.BridgeActivity"))
        {
            jc.CallStatic("SetToast", "hello world");
        }
    }
}
