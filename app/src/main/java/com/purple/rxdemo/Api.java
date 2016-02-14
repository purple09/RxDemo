package com.purple.rxdemo;

import com.purple.rxdemo.fastjson.WordResult;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

    @POST("/tutusoft/shajj/shajj")
    @Headers("apikey=9e38d8a3bfe22646768a559556ec15a5")
    Observable<WordResult> word(@Body MyEntity myEntity);
}
