using UnityEngine;
using System.Collections;

public class StartNextMain : MonoBehaviour {

    private float TimeLeft = 1.0f;

    public void NextScene()
    {
        Application.LoadLevel("MainScene");
    }
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        if (Time.time > TimeLeft)
        {
            NextScene();
        }
	
	}
}
