package com.example.yoon.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yoon on 2016-06-13.
 */
public class TestActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act);

        Button button =  (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Test Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
