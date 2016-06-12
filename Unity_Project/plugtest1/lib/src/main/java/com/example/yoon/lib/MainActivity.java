package com.example.yoon.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void Call(Activity activity) {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
    }
}