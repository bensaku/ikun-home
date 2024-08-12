package com.example.myapplication.bean;

import java.util.List;

public class DataBean {
    public List<GameBean> records;

    public int total;

    public int size;

    public int current;

    public int pages;
    public List<GameBean> getRecords() {
        return records;
    }

    public int getTotal() {
        return total;
    }

    public int getSize() {
        return size;
    }

    public int getCurrent() {
        return current;
    }

    public int getPages() {
        return pages;
    }

    public DataBean(List<GameBean> records, int total, int size, int current, int pages) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
        this.pages = pages;
    }




}
