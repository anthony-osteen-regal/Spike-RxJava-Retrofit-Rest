package com.regal.spike.RestApi.Apis;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RegalRxApi {

    @Headers({
            "Accept: blahab",
            "User-agent: blahaha"
    })
    @GET("/some/url/{with}/{parameters}")
    Observable<String> getFoo();
}
