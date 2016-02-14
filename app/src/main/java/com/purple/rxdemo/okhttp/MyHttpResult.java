package com.purple.rxdemo.okhttp;

import org.json.JSONObject;

public class MyHttpResult {

    public int code;

    public JSONObject body;

    public MyHttpResult(int code, JSONObject body) {
        this.code = code;
        this.body = body;
    }

}
