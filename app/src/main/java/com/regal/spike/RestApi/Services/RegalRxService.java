package com.regal.spike.RestApi.Services;

import com.regal.spike.RestApi.Apis.RegalRxApi;
import com.regal.spike.RestApi.Models.EchoResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This will be mostly how we interface with the regal api
 */
public class RegalRxService {

    private RegalRxApi regalRxApi;
    private static final String PRIMARY_KEY = "a3434522436a4e07a795a49a10184cf7";
    private static final String SECONDARY_KEY = "7e554032ee894feca5d116e4bf3ab7f9";
    private static final String AUTH_USER = "MobileApp1810i_520F5FD5E0B440C697E9EF035C1FFCB5";

    public RegalRxService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.regmovies.com/v1/")
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        regalRxApi = retrofit.create(RegalRxApi.class);
    }

    public Observable<String> echo(String echoString ){
        return regalRxApi.getFoo(AUTH_USER, PRIMARY_KEY, echoString)
                .map(EchoResponse::getEchoString)
                .distinct();
    }

    public Observable<String> logIn(String userName, String password ){
        return regalRxApi.logIn(AUTH_USER, PRIMARY_KEY, userName, password)
                .map(EchoResponse::getEchoString)
                .distinct();
    }
}
