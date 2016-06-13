package com.example.yoon.lib;

import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by Yoon on 2016-05-16.
 */public class StartActivity extends Activity {

    private String TAG = "Plug.StartActivity";
    //static final LatLng SEOUL = new LatLng (37.56,126.97);
    //static private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
        //map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    public static void Call(Activity activity)
    {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
        //Toast.makeText(activity, "Hellow", Toast.LENGTH_SHORT).show();
        /*
        Intent intent = new Intent();
        ComponentName name = new ComponentName("com.example.yoon.lib","com.example.yoon.lib.MapPlugin");
        intent.setComponent(name);
        activity.startActivity(intent);
        */
    }

    public void ClickFunc(View v){

        Toast.makeText(getApplicationContext(), "Hellow", Toast.LENGTH_SHORT).show();
        //setContentView(R.layout.activity_main22);
        Intent intent = new Intent(getApplicationContext(), Main22Activity.class);
        startActivity(intent);
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


