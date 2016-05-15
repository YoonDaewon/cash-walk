using UnityEngine;
using System.Collections;

public class Plugin : MonoBehaviour
{

    public void OnClickButton()
    {
        using (AndroidJavaClass jc = new AndroidJavaClass("com.example.yoon.lib.NativePlugin"))
        {
            jc.CallStatic("showToast", "Test");
  
        }
    }
}
