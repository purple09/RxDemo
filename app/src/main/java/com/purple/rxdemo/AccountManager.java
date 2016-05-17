package com.purple.rxdemo;

import android.os.Handler;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/4/25 14:04
 * @Modifier: guizhen
 * @Update: 2016/4/25 14:04
 */
public class AccountManager {

    private static AccountManager ins = null;

    private String accountName = "";

    private PublishSubject<String> publishSubject;
    private BehaviorSubject<String> behaviorSubject;

    private static final int delay = 0;

    private AccountManager() {
        init();
    }

    public static AccountManager ins() {
        if (ins == null) {
            ins = new AccountManager();
        }
        return ins;
    }

    private void init() {
        publishSubject = PublishSubject.create();
        behaviorSubject = BehaviorSubject.create();
    }

    public Observable<String> rx() {
        return publishSubject;
    }

    public Observable<String> rxBehavior() {
        return behaviorSubject;
    }

    /**
     * 实际应用的时候，data一般是对象，初始化为null，get的返回值需要判断，如果是null，可以{@link #refresh()}或者{@link #getRefresh()}.
     *
     * @return
     */
    public String get() {
        return accountName;
    }

    public void set(String accountName) {
        this.accountName = accountName;
        next(this.accountName);
    }

    public void refresh() {
        Log.e("account_manager", "refresh");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String name = new SimpleDateFormat("HH:mm:ss").format(new Date());
                Log.e("account_manager", name);
                set(name);
            }
        }, delay);
    }

    public void clear() {
        set("");
    }

    public Observable<String> getRefresh() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String name = new SimpleDateFormat().format(new Date());
                        set(name);
                        subscriber.onNext(name);
                        subscriber.onCompleted();
                    }
                }, delay);
            }
        });
    }

    private void next(String accountName) {
        publishSubject.onNext(accountName);
        behaviorSubject.onNext(accountName);
    }

}
