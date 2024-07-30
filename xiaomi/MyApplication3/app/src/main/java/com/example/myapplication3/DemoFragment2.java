package com.example.myapplication3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class DemoFragment2 extends Fragment {
    public String TAG = "DemoFragment2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_fragment,container,false);
        TextView textView = view.findViewById(R.id.tv_tag);
        String text = "当前位置："+getArguments().getInt("params");
        textView.setText(text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("bensaku", TAG+ "onViewCreated");
    }

    public DemoFragment2(){
        super(R.layout.pager_fragment);
        TAG = TAG +" "+ UUID.randomUUID().toString().substring(0,4)+" ";
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("bensaku", TAG+"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("bensaku", TAG+ "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("bensaku", TAG+"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("bensaku", TAG+"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("bensaku", TAG+ "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("bensaku", TAG+"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("bensaku", TAG+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("bensaku", TAG+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("bensaku", TAG+ "onDetach");
    }
}
