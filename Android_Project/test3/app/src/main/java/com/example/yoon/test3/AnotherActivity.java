package com.example.yoon.test3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AnotherActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_main);

        Button backButton = (Button) findViewById(R.id.backbutton);
        backButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", "Daewon");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}