package com.purple.rxdemo;

import com.alibaba.fastjson.JSON;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/2/1 14:25
 * @Modifier: guizhen
 * @Update: 2016/2/1 14:25
 */
public class MyEntity {

    public String str;

    public MyEntity(String content) {
        this.str = content;
    }

     @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
