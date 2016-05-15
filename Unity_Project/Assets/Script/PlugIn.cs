using UnityEngine;
using System.Collections;

public class PlugIn : MonoBehaviour {
    public void CallFromNavtive(string _strLog)
    {
        Debug.Log(_strLog);
    }
}
