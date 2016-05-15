using UnityEngine;
using System.Collections;

public class NativeManager : MonoBehaviour {

	// Use this for initialization
    public void OnclickButton()
    {
        using (AndroidJavaClass javaClass = new AndroidJavaClass("com.jaewon.lib.NativePlugin"))
        {
            javaClass.CallStatic("showToast", "Test");
        }
    }
}
