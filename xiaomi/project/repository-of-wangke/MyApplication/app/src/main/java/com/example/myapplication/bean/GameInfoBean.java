package com.example.myapplication.bean;

public class GameInfoBean {

    private int code;
    private String msg;

    private GameBean data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(GameBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public GameBean getData() {
        return data;
    }

    public GameInfoBean(int code, String msg, GameBean data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
