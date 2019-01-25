package com.regal.spike.RestApi.Apis;

import com.regal.spike.RestApi.Models.EchoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RegalRxApi {

    @GET("GettingStarted/Echo/{body}")
    Observable<EchoResponse> getFoo(
            @Header("AuthUser") String authUser,
            @Header("Ocp-Apim-Subscription-Key") String key,
            @Path("body") String echoString
    );
}
