package com.purple.rxdemo.okhttp;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/2/1 16:44
 * @Modifier: guizhen
 * @Update: 2016/2/1 16:44
 */
public class HttpTransformer<T> {


    public  Observable.Transformer<T, T> instance() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
