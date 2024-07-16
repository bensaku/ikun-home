package com.example.rxjavademo;

import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final int countReserved;

    public MainViewModelFactory(int countReserved) {
        this.countReserved = countReserved;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(countReserved);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

