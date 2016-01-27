package com.purple.rxdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import rx.subjects.PublishSubject;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.cx.rxdemo.MyBroadcastReceiver";
    public static PublishSubject<Intent> mSubject = PublishSubject.create();

    public void onReceive(Context context, Intent intent) {
        if ((intent != null) && ("com.cx.rxdemo.MyBroadcastReceiver".equals(intent.getAction())))
            mSubject.onNext(intent);
    }
}
