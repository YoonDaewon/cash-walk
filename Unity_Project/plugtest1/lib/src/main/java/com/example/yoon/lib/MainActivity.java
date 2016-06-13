package com.example.yoon.lib;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayer;
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
    }*/
}

