package com.example.yoon.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1);
    }

    public void onbackButtonClicked(View v){
        Toast.makeText(getApplicationContext(),"돌아가기 버튼을 누름", Toast.LENGTH_LONG).show();
        finish();
    }
}
