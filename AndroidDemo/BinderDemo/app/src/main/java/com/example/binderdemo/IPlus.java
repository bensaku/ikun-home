package com.example.binderdemo;

import android.os.IInterface;

public interface IPlus extends IInterface {
    public int add(int a, int b);
    String DESCRIPTOR = "com.example.binderdemo.IPlus";
}
