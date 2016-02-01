package com.purple.rxdemo.okhttp;

import org.json.JSONObject;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/2/1 11:07
 * @Modifier: guizhen
 * @Update: 2016/2/1 11:07
 */
public class MyHttpResult {

    public int code;

    public JSONObject body;

    public MyHttpResult(int code, JSONObject body) {
        this.code = code;
        this.body = body;
    }

}
