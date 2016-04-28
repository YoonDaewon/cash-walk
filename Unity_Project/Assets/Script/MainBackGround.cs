using UnityEngine;
using System.Collections;

public class MainBackGround : MonoBehaviour {

    public float speed = 0.5f;

    Material mt;
	// Use this for initialization
	void Start () {
        mt = GetComponent<Renderer>().material;
	}
	
	// Update is called once per frame
	void Update () {
        float offsetY = mt.mainTextureOffset.y + (Time.deltaTime * speed);
        Vector2 modifyOffsetY = new Vector2(0, offsetY);
        mt.mainTextureOffset = modifyOffsetY;
	}
}
