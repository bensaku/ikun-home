package com.example.rxjavademo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AppService {
    @GET("users")
    Observable<List<User>> getAppData();
}
