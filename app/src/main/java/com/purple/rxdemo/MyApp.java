package com.purple.rxdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/4/11 16:58
 * @Modifier: guizhen
 * @Update: 2016/4/11 16:58
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
