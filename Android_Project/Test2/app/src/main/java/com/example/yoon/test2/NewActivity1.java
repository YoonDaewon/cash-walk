package com.example.yoon.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                EditText edit = (EditText) findViewById(R.id.edit);
                String str = edit.getText().toString();
                Toast.makeText(NewActivity1.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onbackButtonClicked(View v){
        Toast.makeText(getApplicationContext(),"돌아가기 버튼을 누름", Toast.LENGTH_LONG).show();
        finish();
    }
}
