package com.kpu.cashwalktmap;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by ydwin on 2016-08-30.
 */
public class ApplicationController extends Application {

    private static ApplicationController instance ;
    //public static ApplicationController getInstance() { return instance ; }

    private static final String TAG = "ApplicationControler";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "this : " + this);
        Log.i(TAG, "instance : " + instance);
        ApplicationController.instance = this; //어플리케이션이 처음 실행될 때 인스턴스를 생성합니다.
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }
        return instance;
    }

    private NetworkService networkService;
    public NetworkService getNetworkService() { return networkService; }

    private String baseUrl; //이번 세미나에서 baseUrl은 서버파트원들의 ip와 port에 따라 다릅니다.

    // local 서버를 이용할 것이기 대문에 포트 번호만 넣음
    public void buildNetworkService(int port) {
        synchronized (ApplicationController.class) {
            if (networkService == null) {
                baseUrl = String.format("http://localhost:%d", port);
                Gson gson = new GsonBuilder()
                        .create();


//서버에서 json 형식으로 데이터를 보내고 이를 파싱해서 받아오기 위해서 사용합니다.

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                networkService = retrofit.create(NetworkService.class);

                Log.i(TAG, "retrofit : " + retrofit);
            }
        }
    }

    public static void main(String[] args) throws Exception {

    }


}