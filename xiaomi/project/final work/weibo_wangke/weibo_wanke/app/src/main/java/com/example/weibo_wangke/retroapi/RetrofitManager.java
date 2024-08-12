package com.example.weibo_wangke.retroapi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static volatile RetrofitManager retrofitManager;
    private final Retrofit retrofit;

    private final DynamicHeaderInterceptor dynamicHeaderInterceptor;

    private OkHttpClient client;

    private RetrofitManager(){
        dynamicHeaderInterceptor = new DynamicHeaderInterceptor();

        client = new OkHttpClient
                .Builder()
                .addInterceptor(dynamicHeaderInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://hotfix-service-prod.g.mi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public RetrofitApi createApi(){
        return retrofit.create(RetrofitApi.class);
    }


    public static RetrofitManager getInstance(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public void setToken(String token) {
        dynamicHeaderInterceptor.setToken(token);
    }
}
