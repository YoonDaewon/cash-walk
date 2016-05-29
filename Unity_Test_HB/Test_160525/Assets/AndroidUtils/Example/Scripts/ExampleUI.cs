using UnityEngine;
using System.Collections;

[ExecuteInEditMode]
public class ExampleUI : MonoBehaviour 
{
	public string message = "This is a toast message";

	void OnGUI() 
	{
		int h = Screen.height;
		int w = Screen.width;

		if (GUI.Button(new Rect(h*0.1f, h*0.1f, w*0.5f, h*0.2f), "Exit Application"))
			AndroidConnector.AndroidQuit();
		
		if (GUI.Button(new Rect(h*0.1f, h*0.4f, w*0.5f, h*0.2f), "Show Toast"))
			AndroidConnector.AndroidToast(message);
		
	}
}
