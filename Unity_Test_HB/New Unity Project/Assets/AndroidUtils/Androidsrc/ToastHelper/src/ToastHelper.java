package net.gamya.android.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastHelper 
{
	private Activity activity;
	
	public ToastHelper(Activity activity) 
	{
		this.activity = activity;
	}

	public void showToast(String text) 
	{
		final String t = text;
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{
				Toast.makeText(activity.getApplicationContext(), 
					t != null? t : "",
   					Toast.LENGTH_LONG).show();
			}
		});
	}
}