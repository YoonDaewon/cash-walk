using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour {
    public void OnClickButton()
    {
        using (AndroidJavaClass jc = new AndroidJavaClass("com.example.yoon.lib.NativePlugin")) 
        {
            jc.CallStatic("showToast", "Success!");
        }
    }
}