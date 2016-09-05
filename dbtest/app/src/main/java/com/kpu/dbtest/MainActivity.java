package com.kpu.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DB_Adapter yoon_db;
    Button btnOpen, btnInsert, btnSelect, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpen = (Button) findViewById(R.id.btnOpen);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnOpen.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        yoon_db = new DB_Adapter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //오픈
            case R.id.btnOpen:
                yoon_db.open();
                break;
            //삽입
            case R.id.btnInsert:
                yoon_db.insert(20,"01012345678","seoul","ggari");
                break;
            //조회
            case R.id.btnSelect:
                Cursor all_cursor = yoon_db.AllRows();
                all_cursor.moveToFirst();
                if(all_cursor.getCount() ==0) {
                    Log.d("TEST","아무것도없다.");
                    return;
                }else{
                    Log.d("TEST", "id=" + all_cursor.getString(all_cursor.getColumnIndex("ID")));
                    Log.d("TEST", "AGE=" + all_cursor.getString(all_cursor.getColumnIndex("AGE")));
                    Log.d("TEST", "PHONE=" + all_cursor.getString(all_cursor.getColumnIndex("PHONE")));
                    Log.d("TEST", "ADDR=" + all_cursor.getString(all_cursor.getColumnIndex("ADDR")));
                    Log.d("TEST", "NAME=" + all_cursor.getString(all_cursor.getColumnIndex("NAME")));
                }
                break;
            //업데이트
            case R.id.btnUpdate:
                yoon_db.update("1",30,"01011112222","busan","rigga");
                break;
            //삭제
            case R.id.btnDelete:
                yoon_db.deleteAll();
                break;


        }
    }
}