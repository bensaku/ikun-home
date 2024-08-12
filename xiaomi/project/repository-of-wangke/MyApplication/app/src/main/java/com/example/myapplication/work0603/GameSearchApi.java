package com.example.myapplication.work0603;

import com.example.myapplication.bean.BaseBean;
import com.example.myapplication.bean.DataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameSearchApi {
    @GET("/quick-game/game/search")
    Call<BaseBean<DataBean>> searchGames(@Query("search") String search,
                                         @Query("current") int current);

    @GET("/quick-game/game/search")
    Call<BaseBean<DataBean>> searchGames(@Query("search") String search);

}
