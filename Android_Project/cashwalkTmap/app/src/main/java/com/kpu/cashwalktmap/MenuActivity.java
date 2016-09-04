package com.kpu.cashwalktmap;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ydwin on 2016-08-16.
 */

public class MenuActivity extends AppCompatActivity{

    private SoundPool mSoundPool;
    private int streamid;
    private int soundid;

    private String userId;
    private double userRecord;
    private int userCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        //Pool 생성
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        // 사운드 로드
        soundid = mSoundPool.load(this, R.raw.track2,1);
        // 사운드 재생
        streamid = mSoundPool.play(soundid, 1.0f, 1.0f, 1, -1, 1.0f);

        Intent intent = getIntent();
        userId = intent.getStringExtra(("UserID"));
        userRecord = intent.getDoubleExtra("UserRecord",0);
        userCash = intent.getIntExtra("UserCash",0);

        Toast.makeText(this,"환영합니다 " + userId + " 님", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        streamid = mSoundPool.play(soundid, 1.0f, 1.0f, 1, -1, 1.0f);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // 화면 전환시 노래 종료
        mSoundPool.stop(streamid);
    }
    @Override
    protected void onDestroy() {

        // save map view state such as map center position and zoom level.
        //saveInstanceState();

        super.onDestroy();
    }

    public void ChangePlayScene(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void ChangeTAScene(View v){
        String strMessage = "준비중입니다.";
        Common.showAlertDialog(MenuActivity.this, "Time Attack\n", strMessage);
    }

    public void ChangeMPScene(View v){
        String strMessage = String.format("\n ID : %s\n Record : %f\n Cash : %d",userId,userRecord,userCash);
        Common.showAlertDialog(MenuActivity.this, "My Page\n", strMessage);
    }

    public void Exit(View v){
        new AlertDialog.Builder(this)
                .setTitle("Cash Walk")
                .setMessage("프로그램을 종료하시겠습니까?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No",  null).show();
    }
}
