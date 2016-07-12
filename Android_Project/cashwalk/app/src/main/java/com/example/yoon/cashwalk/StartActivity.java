package com.example.yoon.cashwalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Yoon on 2016-07-05.
 */
public class StartActivity extends AppCompatActivity{

    protected  void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_start);
    }

    // 버튼을 누르면 메인 엑티비티로 ㄱㄱ
    public void onButtonClicked(View v){
        Intent intent;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
