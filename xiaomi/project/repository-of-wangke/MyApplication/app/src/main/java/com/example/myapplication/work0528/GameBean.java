package com.example.myapplication.work0528;

public class GameBean {
    private int mGameIcon;
    private String mGameName;

    public GameBean() {

    }


    // 构造函数
    public GameBean(int gameIcon, String gameName) {
        this.mGameIcon = gameIcon;
        this.mGameName = gameName;
    }

    // 获取游戏图标的方法
    public int getGameIcon() {
        return mGameIcon;
    }

    // 设置游戏图标的方法
    public void setGameIcon(int gameIcon) {
        this.mGameIcon = gameIcon;
    }

    // 获取游戏名称的方法
    public String getGameName() {
        return mGameName;
    }

    // 设置游戏名称的方法
    public void setGameName(String gameName) {
        this.mGameName = gameName;
    }
}

