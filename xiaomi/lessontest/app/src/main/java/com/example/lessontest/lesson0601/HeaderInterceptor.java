package com.example.lessontest.lesson0601;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder =request.newBuilder();
        builder.addHeader("token","token");
        builder.addHeader("mobileType","mobileType");
        return chain.proceed(builder.build());
    }
}