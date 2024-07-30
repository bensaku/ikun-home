package com.example.myapplication.woek0527;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

public class MainFragment extends Fragment {
    public MainFragment(){
        super(R.layout.work0527_main_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work0527_main_fragment, container, false);
        MyPagerAdapter myPagerAdapter =new MyPagerAdapter(getChildFragmentManager());
        ViewPager pager =view.findViewById(R.id.work0527_pager);
        pager.setAdapter(myPagerAdapter);
        return view;
    }
}
