package com.example.yoon.lib;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import java.util.Map;

public class Main22Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.setId('s');
        setContentView(mainLayout);

        Log.d("MyMessages", "ViewActivity Before newInstance");
        MapFragment frag = MapFragment.newInstance(); //This is where it used to crash with it's damn classnotfound exception
        Log.d("MyMessages", "in ViewActivity After newInstance");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(mainLayout.getId(), frag);
        fragmentTransaction.commit();
*/

    }
    public void showAndroidView()
    {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
            public void run()
            {
                Intent intent = new Intent(UnityPlayer.currentActivity.getApplicationContext(), MapPlugin.class);
                startActivity(intent);
            }

        });
    }
    public void ClickFunc(View v){

        Toast.makeText(getApplicationContext(), "hm...", Toast.LENGTH_SHORT).show();
        //setContentView(R.layout.activity_main22);
    }


}
