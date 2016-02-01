package com.purple.rxdemo.okhttp;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

public class MyHttp {

    private static OKClient client = OKClient.instance();

    public static Observable<MyHttpResult> post(String url, String json) {
        if (json == null) {
            json = "";
        }
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "9e38d8a3bfe22646768a559556ec15a5");
        return Observable.create(new MyHttpSubscribe(url, json, headers, client));
    }

}
