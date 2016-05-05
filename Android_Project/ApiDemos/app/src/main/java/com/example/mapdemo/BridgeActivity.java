package com.example.mapdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-05.
 */
public class BridgeActivity extends UnityPlayerActivity {
    public static final String TAG = "YOUR-TAG-NAME";

    public static void callMap(Context ctx, String uuid){
        Log.i(TAG,"BridgeActivity callMap begin" );

        if(null== Looper.myLooper()){
            Looper.prepare();
        }
        if(null == ctx){
            Log.i(TAG,"Unity ctx is not null");
        }
        Intent mapIntent = new Intent("com.example.mapdemo.MainActivity");

        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.putExtra("UUID", uuid);
        ctx.startActivity(mapIntent);

        UnityPlayer.UnitySendMessage("MapLogic", "called", "yes");
        Log.i(TAG,"BridgeActivity callMap after");

    }
}
