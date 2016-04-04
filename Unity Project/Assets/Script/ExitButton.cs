using UnityEngine;
using System.Collections;

public class ExitButton : MonoBehaviour {

    public void ExitBt(int num)
    {
        if (num==2)
        {
            Application.Quit();
        }
    }
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
