package com.purple.rxdemo;

import com.google.gson.Gson;

public class MyEntity {

    public String content;

    public MyEntity(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
