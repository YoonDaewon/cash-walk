using UnityEngine;
using System.Collections;

public class AndroidConnector
{
	public const string UnityActivityClassName = "com.unity3d.player.UnityPlayer";

	public static void AndroidQuit()
	{
		#if UNITY_ANDROID
		AndroidJavaClass playerClass = new AndroidJavaClass(UnityActivityClassName);
		AndroidJavaObject activity =
			playerClass.GetStatic<AndroidJavaObject>("currentActivity");

		AndroidJavaObject activityUtils = new AndroidJavaObject(
			"net.gamya.android.utils.QuitHelper", activity);
		
		activityUtils.Call ("quit");
		#endif
	}

	public static void AndroidToast(string text)
	{
		#if UNITY_ANDROID
		AndroidJavaClass playerClass = new AndroidJavaClass(UnityActivityClassName);
		AndroidJavaObject activity =
			playerClass.GetStatic<AndroidJavaObject>("currentActivity");

		AndroidJavaObject activityUtils = new AndroidJavaObject(
			"net.gamya.android.utils.ToastHelper", activity);
		
		activityUtils.Call ("showToast", text);
		#endif
	}
}
