package com.example.myapplication.work0603;

import com.example.myapplication.work0602.HeaderInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameRetrofitManager {

    private static volatile GameRetrofitManager retrofitManager;
    private final Retrofit retrofit;

    private OkHttpClient client;

    private GameRetrofitManager(){
        client = new OkHttpClient
                .Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://hotfix-service-prod.g.mi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public GameSearchApi createApi(){
        return retrofit.create(GameSearchApi.class);
    }


    public static GameRetrofitManager getInstance(){
        if (retrofitManager == null){
            synchronized (GameRetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager = new GameRetrofitManager();
                }
            }
        }
        return retrofitManager;
    }
}
