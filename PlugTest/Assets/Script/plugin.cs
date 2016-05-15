using UnityEngine;
using System.Collections;

public class plugin : MonoBehaviour {

    public void OnClickButton()
    {
        using (AndroidJavaClass javaCalss = new AndroidJavaClass("com.example.yoon.lib.NativePlugin"))
        {
            javaCalss.CallStatic("showToast", "Test");
            javaCalss.CallStatic("onCreate");
        }
    }
}
