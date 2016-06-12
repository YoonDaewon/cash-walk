package com.example.yoon.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends Activity {

    public static final int REQUEST_CODE_ANOTHER = 1001;

    private String TAG = "Plug.StartActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "Activity created");
    }

    public static void Call(Activity activity) {
        // Creating an intent with the current activity and the activity we wish to start
        Intent myIntent = new Intent(activity, StartActivity.class);
        activity.startActivity(myIntent);
        Toast.makeText(activity, "Hellow", Toast.LENGTH_SHORT).show();
    }

    public void onButtonClicked(View v) {
        Intent intent = new Intent(getBaseContext(), MapPlugin.class);
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // 또 다른 액티비티에서 보내온 응답인지 요청코드로 확인
        if (requestCode == REQUEST_CODE_ANOTHER) {
            // 토스트로 메시지 보내기
            Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() 메소드가 호출됨. 요청코드 : " + requestCode + ", 결과코드 : " + resultCode, Toast.LENGTH_LONG);
            toast.show();


            if (resultCode == RESULT_OK) {
                String name = intent.getExtras().getString("name");
                toast = Toast.makeText(getBaseContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}


 //   public void showAndroidView()
//    {
 //       Log.d("MyMessages", "in showAndroidView");
 //       UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
 //               Log.d("MyMessages", "Running showAndroidView");




    //            Intent intent = new Intent(UnityPlayer.currentActivity.getApplicationContext(), MapPlugin.class);
   //             UnityPlayer.currentActivity.startActivity(intent);
   //         }
  //      });
  //  }
  //  public void showToast(final String message){
  //      final Activity activity = UnityPlayer.currentActivity;
  //      activity.runOnUiThread(new Runnable(){
  //          public void run(){
  //              Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
  //          }
  //      });
  //  }

