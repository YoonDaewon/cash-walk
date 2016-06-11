package com.example.yoon.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Yoon on 2016-05-16.
 */
public class StartActivity extends Activity  {

    private String TAG = "Plug.StartActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity created");
    }

    public static void Call(Activity activity)
    {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
    }
/*
    public static void showAndroidView()
    {
        final Activity activity = UnityPlayer.currentActivity;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "StartActivity create", Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void Call(Activity activity)
    {
       Intent i = new Intent("com.example.yoon.lib.secondview");
        activity.startActivity(i);
    }
    */
}