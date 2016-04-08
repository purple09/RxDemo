package com.purple.rxdemo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description: Rx的工具类
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/4/8 10:44
 * @Modifier: guizhen
 * @Update: 2016/4/8 10:44
 */
public class RxUtils {

    /** io线程执行，主线程观察。
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> schedulersTransformer() {
        return Observable -> Observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
