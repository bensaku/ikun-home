package com.example.weibo_wangke.retroapi;

import com.example.weibo_wangke.bean.BaseBean;
import com.example.weibo_wangke.bean.CodeRequestBody;
import com.example.weibo_wangke.bean.LikeRequestBody;
import com.example.weibo_wangke.bean.LoginBean;
import com.example.weibo_wangke.bean.LoginRequestBody;
import com.example.weibo_wangke.bean.RecordsBean;
import com.example.weibo_wangke.bean.UserInfoBean;
import com.example.weibo_wangke.bean.WeiboInfoBean;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitApi {
    @POST("/weibo/api/auth/sendCode")
    Call<BaseBean<Object>> sendCode(@Body CodeRequestBody codeRequestBody);

    @POST("/weibo/api/auth/login")
    Call<BaseBean<String>> postLogin(@Body LoginRequestBody loginRequestBody);

    @GET("/weibo/api/user/info")
    Call<BaseBean<UserInfoBean>> getUserInfo();

    @GET("/weibo/homePage")
    Call<BaseBean<RecordsBean>> getHomePage(@Query("current")int current);

    @POST("/weibo/like/up")
    Call<BaseBean<Boolean>> postLike(@Body LikeRequestBody likeRequestBody);

    @POST("/weibo/like/down")
    Call<BaseBean<Boolean>> postDislike(@Body LikeRequestBody likeRequestBody);

    @GET
    Call<ResponseBody> downloadImage(@Url String fileUrl);
}
