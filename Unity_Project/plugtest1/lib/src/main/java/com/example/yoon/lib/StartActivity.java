package com.example.yoon.lib;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */public class StartActivity extends Activity {
    private String TAG = "Plug.StartActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Activity created");
    }

    public static void Call(Activity activity)
    {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
        Toast.makeText(activity, "Hellow", Toast.LENGTH_SHORT).show();

    }
}


 //   public void showAndroidView()
//    {
 //       Log.d("MyMessages", "in showAndroidView");
 //       UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
 //               Log.d("MyMessages", "Running showAndroidView");




    //            Intent intent = new Intent(UnityPlayer.currentActivity.getApplicationContext(), MapPlugin.class);
   //             UnityPlayer.currentActivity.startActivity(intent);
   //         }
  //      });
  //  }
  //  public void showToast(final String message){
  //      final Activity activity = UnityPlayer.currentActivity;
  //      activity.runOnUiThread(new Runnable(){
  //          public void run(){
  //              Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
  //          }
  //      });
  //  }


