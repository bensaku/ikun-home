package com.example.rxjavademo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    MainViewModel(int countReserved){
        count.setValue(countReserved);
    }

    public int getCount() {
        return count.getValue();
    }

    public void incrementCount() {
        Integer value = count.getValue();
        if (value != null) {
            count.setValue(value + 1);
        }
    }
}
