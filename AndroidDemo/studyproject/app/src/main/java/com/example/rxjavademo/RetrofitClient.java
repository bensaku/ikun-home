package com.example.rxjavademo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    final String url = "https://jsonplaceholder.typicode.com/";
    private static RetrofitClient instance;
    private final AppService appService;

    // 私有构造函数
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        appService = retrofit.create(AppService.class);
    }
    // 获取单例实例
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // 获取ApiService
    public AppService getApiService() {
        return appService;
    }
}
