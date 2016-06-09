package net.gamya.android.utils;

import android.app.Activity;

public class QuitHelper 
{
	private Activity activity;

	public QuitHelper(Activity activity) {
		this.activity = activity;
	}

	public void quit() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{
				activity.moveTaskToBack(true);
			}
		});
	}

}
