package com.kpu.cashwalktmap;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ydwin on 2016-08-16.
 */

public class MenuActivity extends AppCompatActivity{

    private SoundPool mSoundPool;
    private int streamid;
    private int soundid;

    // activity간 데이터 교환을 위한 변수
    private String userId;
    private String userPw;
    private double userRecord;
    private int userCash;
    private Data userData;

    final static int ACTIVITY_CODE = 0;

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

        // Activity 전환 시 공유 Data 값 받아옴
        Intent intent = getIntent();
        userId = intent.getStringExtra("UserID");
        userPw =  intent.getStringExtra("UserPW");
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

        // Intent시 데이터 저장하여 보냄 MainActivity로 보냄.
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("UserID", userId);
        intent.putExtra("UserPW",userPw);
        intent.putExtra("UserRecord", userRecord);
        intent.putExtra("UserCash", userCash);
        startActivityForResult(intent, ACTIVITY_CODE);

        /*
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        */
    }

    public void ChangeTAScene(View v){
        Intent intent = new Intent(getApplicationContext(),TimeActivity.class);
        startActivity(intent);
    }

    public void ChangeMPScene(View v){
        // MyPage를 부를 때 마다 통신해서 사용자 데이터를 업데이트 함
        GetData();
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

    // MyPage 업데이트를 위한 함수. 서버에서 직접 데이터를 가져옴
    public void GetData(){

        // ip, port 연결, network 연결
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.139:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkService networkService = retrofit.create(NetworkService.class);

        Call<Data> call = networkService.getLoginId(userId);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                userData = response.body();

                saveData();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    // DB에서 가져온 정보를 저장함
    private void saveData(){
        userId = userData.getId();
        userPw = userData.getPw();
        userRecord = userData.getRecord();
        userCash = userData.getCash();
    }
}
