package com.example.yoon.lib;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */
public class StartActivity extends UnityPlayerActivity {

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

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
}