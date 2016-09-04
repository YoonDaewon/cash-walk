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

    private static final String TAG = "TitleActivity";
    //retrofit 변수 선언
    Retrofit retrofit;
    NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        // 서버와 통신 위한
        // ip, port 연결, network 연결
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.183:3000/")
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

        // save map view state such as map center position and zoom level.
        //saveInstanceState();

        super.onDestroy();
    }

    public void TouchScreen(View v) {
        showAlertDialog();
        /*
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        */
    }

    private void showAlertDialog(){
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout)vi.inflate(R.layout.dialog_login, null);

        final EditText id = (EditText)loginLayout.findViewById(R.id.id);
        final EditText pw = (EditText)loginLayout.findViewById(R.id.pw);

        AlertDialog.Builder adb =  new AlertDialog.Builder(this);

        adb.setTitle("로그인");
        adb.setView(loginLayout);
        adb.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                LoginCheck();

                Toast.makeText(
                        TitleActivity.this, "ID : " +
                                id.getText().toString() + "\nPW : " +
                                pw.getText().toString(), Toast.LENGTH_LONG).show();

                // 디비 확인 후 데이터 없으면 insert
            }
        }).show();
    }

    // 로그인 통신 하기 위한 함수
    public void LoginCheck(){
        Call<Data> call = networkService.getLoginId("yoon");

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Sucesss",response.body().toString());
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
    }
}
