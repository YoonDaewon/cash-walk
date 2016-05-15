package com.example.yoon.newgps;

import android.widget.Toast;

import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-14.
 */
public class BridgeActivity extends UnityPlayerActivity {
    public  void  SetToast(final String msg){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BridgeActivity.this, msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}

