package com.example.myapplication3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class PagerFragment extends Fragment {

    public PagerFragment(){
        super(R.layout.pager_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_fragment,container,false);
        TextView textView = view.findViewById(R.id.tv_tag);
        String text = "当前位置："+getArguments().getInt("params");
        textView.setText(text);
        return view;
    }
}
