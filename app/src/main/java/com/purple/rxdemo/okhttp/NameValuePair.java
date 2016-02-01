package com.purple.rxdemo.okhttp;

/**
 * @Description: 请求参数key/value对
 * @Copyright: Copyright (c) 2015 chexiang.com. All right reserved.
 * @Author: zhangshunjie
 * @Date: 2015/11/17
 * @Modifier: zhangshunjie
 * @Update: 2015/11/17
 */
public class NameValuePair {

    private String name;
    private String value;

    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
