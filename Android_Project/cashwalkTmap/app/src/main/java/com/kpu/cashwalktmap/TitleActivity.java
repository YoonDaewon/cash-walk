package com.kpu.cashwalktmap;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ydwin on 2016-08-16.
 */
public class TitleActivity extends AppCompatActivity {

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);
        activity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
        // 화면 전환시 액티비티 종료
        activity.finish();
    }
    @Override
    protected void onDestroy() {

        // save map view state such as map center position and zoom level.
        //saveInstanceState();

        super.onDestroy();
    }

    public void TouchScreen(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
