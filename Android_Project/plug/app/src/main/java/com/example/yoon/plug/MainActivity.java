package com.example.yoon.plug;

import android.os.Bundle;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

   protected void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);

       UnityPlayer.UnitySendMessage("Main Camera","CallFromNavtive","call test");
    }
}
