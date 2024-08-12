package com.example.weibo_wangke.bean;

public class LoginRequestBody {
    private String phone;

    private String smsCode;

    public LoginRequestBody(String phone,String smsCode) {
        this.smsCode = smsCode;
        this.phone = phone;
    }
}
