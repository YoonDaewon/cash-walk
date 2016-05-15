package com.jaewon.lib;
import android.app.Activity;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
/**
 * Created by 재원 on 2016-05-15.
 */
public class NativePlugin {

    public static void showToast(final String message){
        final Activity activity = UnityPlayer.currentActivity;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
