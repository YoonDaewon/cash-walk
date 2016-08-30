package com.kpu.cashwalktmap;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by ydwin on 2016-08-30.
 */
public class ApplicationController extends Application {

    private static ApplicationController instance ;
    public static ApplicationController getInstance() { return instance ; }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this; //어플리케이션이 처음 실행될 때 인스턴스를 생성합니다.
    }

    private NetworkService networkService;
    public NetworkService getNetworkService() { return networkService; }

    private String baseUrl; //이번 세미나에서 baseUrl은 서버파트원들의 ip와 port에 따라 다릅니다.

    public void buildNetworkService(String ip, int port) {
        synchronized (ApplicationController.class) {
            if (networkService == null) {
                baseUrl = String.format("http://%s:%d", ip, port);
                Gson gson = new GsonBuilder()
                        .create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);
//서버에서 json 형식으로 데이터를 보내고 이를 파싱해서 받아오기 위해서 사용합니다.

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .build();
                networkService = retrofit.create(NetworkService.class);
            }
        }
    }
}