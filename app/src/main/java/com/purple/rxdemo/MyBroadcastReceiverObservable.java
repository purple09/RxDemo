package com.purple.rxdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

public class MyBroadcastReceiverObservable
        implements Observable.OnSubscribe<Intent> {
    private Context context;
    private IntentFilter intentFilter;

    public MyBroadcastReceiverObservable(Context context, IntentFilter intentFilter) {
        this.context = context;
        this.intentFilter = intentFilter;
    }

    public void call(Subscriber<? super Intent> subscriber) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                subscriber.onNext(intent);
            }
        };
        subscriber.add(Subscriptions.create(() -> context.unregisterReceiver(broadcastReceiver)));
        this.context.registerReceiver(broadcastReceiver, this.intentFilter);
    }
}