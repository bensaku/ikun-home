package com.example.weibo_wangke.retroapi;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DynamicHeaderInterceptor implements Interceptor {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder()
                .header("Content-Type", "application/json");

        if (token != null && !token.isEmpty()) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}

