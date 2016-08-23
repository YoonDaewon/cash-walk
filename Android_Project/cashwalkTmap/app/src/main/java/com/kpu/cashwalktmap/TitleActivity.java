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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ydwin on 2016-08-16.
 */
public class TitleActivity extends AppCompatActivity {
    DB_Adapter cw_db;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);
        activity = this;
        cw_db = new DB_Adapter(this);
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
        adb.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        TitleActivity.this,"ID : " +
                                id.getText().toString() + "\nPW : " +
                                pw.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }).show();
    }
}
