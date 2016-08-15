package com.kpu.cashwalktmap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ydwin on 2016-08-16.
 */
public class MenuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
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

    public void ChangePlayScene(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void ChangeTAScene(View v){
        String strMessage = "준비중입니다.";
        Common.showAlertDialog(MenuActivity.this, "Time Attack\n", strMessage);
    }

    public void ChangeMPScene(View v){
        String strMessage = "준비중입니다.";
        Common.showAlertDialog(MenuActivity.this, "My Page\n", strMessage);
    }

    public void Exit(View v){
        new AlertDialog.Builder(this)
                .setTitle("Cash Walk")
                .setMessage("프로그램을 종료하시겠습니까?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No",  null).show();
    }
}
