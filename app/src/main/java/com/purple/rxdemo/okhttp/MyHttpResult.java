package com.purple.rxdemo.okhttp;

public class MyHttpResult {

    public int code;

    public String json;

    public MyHttpResult(int code, String json) {
        this.code = code;
        this.json = json;
    }

}
