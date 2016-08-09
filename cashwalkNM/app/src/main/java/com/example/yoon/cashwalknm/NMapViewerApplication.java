package com.example.yoon.cashwalknm;

import android.app.Application;

/**
 * Created by ydwin on 2016-08-09.
 */
public class NMapViewerApplication extends Application {

    private static NMapViewerApplication instance;

    public static NMapViewerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
    }
}
