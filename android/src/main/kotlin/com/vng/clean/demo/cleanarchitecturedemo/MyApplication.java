package com.vng.clean.demo.cleanarchitecturedemo;

import android.app.Application;

public class MyApplication extends Application {

    private static volatile MyApplication sInstance;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
