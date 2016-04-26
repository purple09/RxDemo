package com.purple.rxdemo;

import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.exceptions.Exceptions;

public class ErrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        rx(
                Observable
                        .create(subscriber -> subscriber.onNext(6))
                        .map(i -> {
                            try {
                                return throwError();
                            } catch (Exception e) {
                                //e.printStackTrace();
                                throw Exceptions.propagate(e);
                            }
                            //return 0;
                        })
                        .subscribe(next -> Log.e("ErrorActivity", "next"),
                                error -> Log.e("ErrorActivity", "error:" + error.getCause().getMessage()),
                                () -> Log.e("ErrorActivity", "complete"))
        );
    }

    public Integer throwError() throws Exception {
        throw new Exception("出错了");
    }

}
