package com.purple.rxdemo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.alibaba.fastjson.JSON;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test(){
        MyEntity entity=new MyEntity("test");
        Log.e("test",JSON.toJSONString(entity));

    }
}