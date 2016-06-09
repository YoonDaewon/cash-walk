package com.example.yoon.lib;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
=======
import android.os.Bundle;
>>>>>>> 62fd1ad8d6a9345c0aa3005adc4ed2d72a4ff450
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */
public class StartActivity extends UnityPlayerActivity {

<<<<<<< HEAD

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    public void ToastMessage()
    {
        handler.sendEmptyMessage(0);
=======
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    public static void showAndroidView()
    {
        final Activity activity = UnityPlayer.currentActivity;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "StartActivity create", Toast.LENGTH_LONG).show();
            }
        });
>>>>>>> 62fd1ad8d6a9345c0aa3005adc4ed2d72a4ff450
    }



    public Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what) {
                case 0:
                Toast.makeText(getApplicationContext(), "ToastMessage Button", Toast.LENGTH_SHORT).show();
                break;

            }
            }

    };



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


}