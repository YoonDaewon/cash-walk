package com.example.yoon.lib;


import android.content.Intent;
import android.util.Log;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */
public class MainActivity extends UnityPlayerActivity {
    public void showAndroidView()
    {
        Log.d("MyMessages", "in showAndroidView");
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                Log.d("MyMessages", "Running showAndroidView");
                Intent intent = new Intent(UnityPlayer.currentActivity.getApplicationContext(), MapPlugin.class);
                UnityPlayer.currentActivity.startActivity(intent);
            }
        });
    }
}
