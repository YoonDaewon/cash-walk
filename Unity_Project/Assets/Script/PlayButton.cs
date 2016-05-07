using UnityEngine;
using System.Collections;

public class PlayButton : MonoBehaviour {
    
    
    public void PlayBt(int num)
    {
        if (num==1)
        {
            Application.LoadLevel("PlayScene");
        }
    }


	// Use this for initialization
	void Start () {
	

	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
