package com.purple.rxdemo.okhttp;

import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

public class MyHttpSubscribe implements Observable.OnSubscribe<MyHttpResult> {

    private String url;
    private String json;
    private Map<String, String> headers;
    private OKClient client;

    private MyHttpSubscribe() {
    }

    public MyHttpSubscribe(String url, String json, Map<String, String> headers, OKClient client) {
        this.url = url;
        this.json = json;
        this.headers = headers;
        this.client = client;
    }

    @Override
    public void call(Subscriber<? super MyHttpResult> subscriber) {

        try {
            Response response = client.execute(HttpMethod.POST, url, null, headers, json);
            if (response.isSuccessful()) {
                subscriber.onNext(new MyHttpResult(response.code(), response.body().string()));
                subscriber.onCompleted();
            } else {
                subscriber.onError(new IOException("Unexpected code " + response));
            }
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }
}
