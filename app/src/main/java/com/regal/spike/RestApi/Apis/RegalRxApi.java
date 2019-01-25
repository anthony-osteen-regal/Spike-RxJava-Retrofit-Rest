package com.regal.spike.RestApi.Apis;

import com.regal.spike.RestApi.Models.EchoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegalRxApi {

    @GET("GettingStarted/Echo/{body}")
    Observable<EchoResponse> getFoo(
            @Header("AuthUser") String authUser,
            @Header("Ocp-Apim-Subscription-Key") String key,
            @Path("body") String echoString
    );

    @GET("CrownClub/Member/Login")
    Observable<EchoResponse> logIn(
            @Header("AuthUser") String authUser,
            @Header("Ocp-Apim-Subscription-Key") String key,
            @Query("credential1") String userName,
            @Query("credential2") String password
    );


}
