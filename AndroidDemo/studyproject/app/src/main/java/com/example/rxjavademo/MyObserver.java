package com.example.rxjavademo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver implements LifecycleEventObserver {

    private static final String tag = "MyObserver";

    @Override
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        switch (event){
            case ON_START:
                Log.i(tag,"onStart");
                break;
            case ON_STOP:
                Log.i(tag,"onStop");
                break;
        }
    }
}
