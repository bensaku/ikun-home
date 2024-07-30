package com.example.myapplication.woek0527;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class PagerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work0527_pager_fragment,container,false);
        TextView textView = view.findViewById(R.id.work0527_tv_tag);
        String text = "首页页面："+getArguments().getInt("params");
        textView.setText(text);
        return view;
    }

}
