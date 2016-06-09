using UnityEngine;
using System.Collections;

public class SentenceTest : MonoBehaviour {


    bool hasClickedButton = false;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnGUI()
    {
        if(!hasClickedButton)
        {
            if(GUI.Button (new Rect(10, 10, 150, 100), "Start Button"))
            {
                hasClickedButton = true;
            }
        }
        else
        {
            GUI.Label(new Rect(10, 10, 100, 20), "it's just starting scene"); 
        }
    }
}
