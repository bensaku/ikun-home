package com.example.myapplication3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    public DemoCollectionPagerAdapter(@NonNull FragmentManager fm){
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        DemoFragment2 demoFragment2 = new DemoFragment2();
        Bundle bundle = new Bundle();
        bundle.putInt("params",position+1);
        demoFragment2.setArguments(bundle);
        return demoFragment2;
    }

    @Override
    public int getCount() {
        return 5;
    }

}

