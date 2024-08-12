package com.example.weibo_wangke.bean;

import java.io.Serializable;
import java.util.List;

public class WeiboInfoBean implements Serializable {

    public Long id;
    public Long userId;
    public String username;
    public String phone;
    public String avatar;
    public String title;
    public String videoUrl;
    public String poster;
    public List<String> images;
    public int likeCount;
    public  boolean likeFlag;
    public String createTime;
}
