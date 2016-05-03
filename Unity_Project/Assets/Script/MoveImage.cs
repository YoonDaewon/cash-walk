using UnityEngine;
using System.Collections;

public class MoveImage : MonoBehaviour {

    public GameObject moveImage_1;
    public GameObject moveImage_2;
    public float speed;

    Vector3 image_Position;

    // Use this for initialization

    void Start () {
        Vector3 start_Position = new Vector3(420.0f,228.0f,0.0f);
        moveImage_1.transform.position = start_Position;
        start_Position = new Vector3(1620.0f, 228.0f, 0.0f);
        moveImage_2.transform.position = start_Position;
        speed = -3.0f;
        image_Position = new Vector3(speed, 0.0f, 0.0f);
	}
	
	// Update is called once per frame
    void Update()
    {
        moveImage_1.transform.position += image_Position;
        moveImage_2.transform.position += image_Position;
        if (moveImage_1.transform.position.x < -780.0f)
        {
            moveImage_1.transform.position = new Vector3(1620.0f, 228.0f, 0.0f);
        }
        if (moveImage_2.transform.position.x < -780.0f)
        {
            moveImage_2.transform.position = new Vector3(1620.0f, 228.0f, 0.0f);
        }
	}
}
