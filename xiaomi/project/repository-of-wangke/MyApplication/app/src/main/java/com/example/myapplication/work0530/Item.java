package com.example.myapplication.work0530;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Item implements MultiItemEntity {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_TEXT = 2;

    private final int type;
    private final String content; // URL for image or text content

    public Item(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getItemType() {
        return type;
    }
}

