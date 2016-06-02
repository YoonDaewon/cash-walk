package com.example.yoon.lib;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */
public class StartActivity extends UnityPlayerActivity {

    public static void showAndroidView(Context ctx, String uuid){

        if(null == Looper.myLooper()){
            Looper.prepare();
        }
        if(null == ctx){
        }

        Intent mapIntent = new Intent("com.example.yoon.lib.MapPlugin");        //ex)com.example.good.activity
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.putExtra("UUID", uuid);
        ctx.startActivity(mapIntent);
//        ctx.startActivityForResult(mapIntent, 0);
        //In order to call Unity function
        UnityPlayer.UnitySendMessage("MapLogic", "called", "yes");

    }
}