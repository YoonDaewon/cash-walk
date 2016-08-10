package com.kpu.cashwalk;

import android.app.Application;

/**
 * Created by ydwin on 2016-08-10.
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
