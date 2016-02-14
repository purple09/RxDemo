package com.purple.rxdemo.okhttp;

import rx.Observable;

public class MyHttp {

    private static OKClient client = OKClient.instance();

    public static Observable<MyHttpResult> post(String url, String json) {
        if (json == null) {
            json = "";
        }
        return Observable.create(new MyHttpSubscribe(url, json, null, client));
    }

}
