package com.example.lessontest.lesson0601;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.bean.GameInfoBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpInstance {
    private final String TAG = "OkhttpInstance";

    private static volatile OkhttpInstance instance;

    private OkHttpClient client;

    private Gson gson = new Gson();

    private OkhttpInstance() {
        client = new OkHttpClient
                .Builder()
                .addInterceptor(new HeaderInterceptor())//加请求头
                .build();
    }

    public static OkhttpInstance getInstance() {
        if (instance == null) {
            synchronized (OkhttpInstance.class) {
                if (instance == null) {
                    instance = new OkhttpInstance();
                }
            }
        }

        return instance;
    }


    //
    public void getSync(String url) {

        new Thread() {
            @Override
            public void run() {
                //创建request，包含url、请求方式、请求体
                Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                //把请求实例包装成可执行的call
                Call call = client.newCall(request);
                try {
                    //向服务器发起请求
                    Response response = call.execute();
                    if (response.body() != null) {
                        String content =response.body().string();
                        GameInfoBean bean = gson.fromJson(content, GameInfoBean.class);
                        Log.i(TAG, response.body().string());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                super.run();
            }
        }.start();
    }

    //异步
    public  void  getAsync(String url,Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call=client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.code() == 200 && response.body()!=null){
                    callback.onResponse(call,response);
                }
            }
        });
    }

    public  void postAsync(String url,Callback callback){
        FormBody formBody = new FormBody.Builder()
                .add("phone", "18156614670")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG,"onfail");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callback.onResponse(call,response);
            }
        });
    }



}
