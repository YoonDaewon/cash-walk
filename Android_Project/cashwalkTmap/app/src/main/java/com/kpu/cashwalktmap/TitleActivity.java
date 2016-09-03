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

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ydwin on 2016-08-16.
 */
public class TitleActivity extends AppCompatActivity {

    //통신위한 변수 선언
    private NetworkService networkService;

    private static final String TAG = "TitleActivity";

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        // 서버와 통신 위한
        // ip, port 연결, network 연결
        ApplicationController application = ApplicationController.getInstance();
        Log.i(TAG, "application : " + application);
        application.buildNetworkService(3000);
        networkService = application.getNetworkService();
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
                try {
                    login("http://localhost:3000/users", id.toString(), pw.toString());
                } catch(Exception e) {
                    e.printStackTrace();
                }

/*
                Call<Data> response = networkService.login("yoon");

                response.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Response<Data> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Data data = response.body();
                            Log.i(TAG, "data : " + data);
                            // 성공시 [변수 이름]에 성공한 데이터 담김
                            //여기에다가 성공할 때 필요한 코드 작성
                        } else {
                            int statusCode = response.code();
                            Log.i(TAG, "응답 코드 : " + statusCode);
                            //서버상에 문제 있을 경우 응답 코드 뜸
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.i(TAG, "Server connection FaIL!!!");
                        Log.i(TAG, "Exception : " + t.getStackTrace());
                        Log.i(TAG, "Message : " + t.getMessage());
                        Log.i(TAG, "message : " + t.getLocalizedMessage());
                        new Exception(t).printStackTrace();
                    }
                });
*/
                Toast.makeText(
                        TitleActivity.this, "ID : " +
                                id.getText().toString() + "\nPW : " +
                                pw.getText().toString(), Toast.LENGTH_LONG).show();

                // 디비 확인 후 데이터 없으면 insert
            }
        }).show();
    }

    private static String login(String url, String id, String pw) throws Exception {

        URL obj = new URL(url + "?id=" + id + "&pw=" + pw);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header


        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }
}
