package com.example.yoon.lib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */
public class StartActivity extends UnityPlayerActivity {

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Toast.makeText(this, "onCreate",Toast.LENGTH_LONG).show();
    }

    public void showAndroidView()
    {
        Log.d("MyMessages", "in showAndroidView");
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyMessages", "Running showAndroidView");
                Intent intent = new Intent(UnityPlayer.currentActivity.getApplicationContext(), MapPlugin.class);
                UnityPlayer.currentActivity.startActivity(intent);
            }
        });
    }
}