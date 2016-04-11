package com.purple.rxdemo;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/4/11 18:02
 * @Modifier: guizhen
 * @Update: 2016/4/11 18:02
 */
public class BaseActivity extends AppCompatActivity {

    private CompositeSubscription rx = new CompositeSubscription();

    public void rx(Subscription subscription) {
        rx.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rx.unsubscribe();
    }

}
