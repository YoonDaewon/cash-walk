package com.example.yoon.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;

import com.unity3d.player.UnityPlayer;
=======
>>>>>>> 13af56205f7fa0f0346cf2cca499a7e166a3201d
import com.unity3d.player.UnityPlayerActivity;


public class MainActivity extends UnityPlayerActivity {
    public static void callMap(Context ctx, String uuid) {
        if (null == Looper.myLooper()) {
            Looper.prepare();

        }

        Intent mapIntent = new Intent("com.example.yoon.lib.intentfilter.secondview");

        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.putExtra("UUID", uuid);
        ctx.startActivity(mapIntent);
        UnityPlayer.UnitySendMessage("MapPlugin", "called", "yes");

    }





/*

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
    }*/



    public static void Call(Activity activity) {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
    }
}