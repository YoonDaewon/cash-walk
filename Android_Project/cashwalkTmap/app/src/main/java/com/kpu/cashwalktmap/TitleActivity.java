package com.kpu.cashwalktmap;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by ydwin on 2016-08-16.
 */
public class TitleActivity extends AppCompatActivity {

    final static int ACTIVITY_CODE = 0;

    private static final String TAG = "TitleActivity";
    //retrofit 변수 선언
    Retrofit retrofit;
    NetworkService networkService;
    // 로그인 정보 저장하기 위한 변수
    private Data userData;

    private String userId;
    private String userPw;
    private double userRecord;
    private int userCash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        // 서버와 통신 위한
        // ip, port 연결, network 연결
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.139:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void TouchScreen(View v) {
        showAlertDialog();

        /*
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        */
    }

    private void showAlertDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.dialog_login, null);

        final EditText id = (EditText) loginLayout.findViewById(R.id.id);
        final EditText pw = (EditText) loginLayout.findViewById(R.id.pw);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("로그인");
        adb.setView(loginLayout);
        adb.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // 통신하기 위한
                Call<Data> call = networkService.getLoginId(id.getText().toString());

                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.d("Connect Success", "good");

                            //통신해서 데이터를 저장
                            userData = response.body();
                            if(userData.getId().equals(id.getText().toString()))
                            {
                                if(userData.getPw().equals(pw.getText().toString())) {

                                    // 입력한 아이디와 비번이 모두 같은 경우
                                    saveData(); // 데이터 저장

                                    // Intent시 데이터 저장하여 보냄
                                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                    intent.putExtra("UserID", userId);
                                    intent.putExtra("UserPW", userPw);
                                    intent.putExtra("UserRecord", userRecord);
                                    intent.putExtra("UserCash", userCash);
                                    startActivityForResult(intent,ACTIVITY_CODE);

                                }
                                else{
                                    // 아이디만 맞고 비번이 틀린경우
                                }
                            }
                            else{
                                // 아이디 비번 둘 다 틀린경우
                            }
                        } else if (response.isSuccessful()) {
                            Log.d("Response Body isNull", response.message());

                        } else {
                            // 404 Not Found
                            Log.d("Response Error Body", response.errorBody().toString());
                        }

                    }
                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Log.i("Server connection Fail", t.getMessage());
                    }
                });

                // 디비 확인 후 데이터 없으면 insert
            }
        }).show();
    }
    // 로그인 정보가 일치하면 데이터를 저장함.
    private void saveData(){
        userId = userData.getId();
        userPw = userData.getPw();
        userRecord = userData.getRecord();
        userCash = userData.getCash();
    }
}