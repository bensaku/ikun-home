package com.example.coroutinesdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.baidu.com/"

    // 创建一个 OkHttpClient 实例，用于添加日志拦截器等配置
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 设置日志级别为BODY，以打印请求和响应信息
        })
        .build()

    // 创建 Retrofit 实例
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()) // 使用 GsonConverter 处理 JSON 数据
        .build()
}