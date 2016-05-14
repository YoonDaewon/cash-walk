package com.example.yoon.newgps;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-14.
 */
public class BridgeActivity extends UnityPlayerActivity {
    public static void callMap(Context ctx, String uuid){
        if(null== Looper.myLooper()){
            Looper.prepare();
        }
        if(null==ctx){

        }
        Intent mapIntent = new Intent("com.example.yoon.newgps.MainActivity");

        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.putExtra("UUID", uuid);
        ctx.startActivity(mapIntent);

        UnityPlayer.UnitySendMessage("MapLogic","called","yes");
    }
}

