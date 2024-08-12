package com.example.myapplication.work0527;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        PagerFragment pagerFragment =new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("params", position + 1);
        pagerFragment.setArguments(bundle);
        return pagerFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

}
