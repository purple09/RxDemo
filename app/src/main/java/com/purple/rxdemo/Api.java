package com.purple.rxdemo;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

    @POST("/testPost")
    Observable<Result> post(@Body MyEntity myEntity);

    @POST("/testGet")
    Observable<Result> get();

}
