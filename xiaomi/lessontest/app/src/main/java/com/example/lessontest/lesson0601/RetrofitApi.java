package com.example.lessontest.lesson0601;

import com.example.myapplication.bean.BaseBean;
import com.example.myapplication.bean.GameBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("quick-game/game/{path}")
    Call<BaseBean<GameBean>> garGameDetail(@Path("path")String path);

    @FormUrlEncoded
    @POST("quick-game/api/auth/sendCodeByFormData")
    Call<BaseBean<Object>> sendMsg(@Field("phone") String phoneNumber);

}
