package com.example.myapplication3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class DemoFragment extends Fragment {

    public interface    TopPageActionListener{
        void onTopPageAction();
    }
    private TopPageActionListener topPagrActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_fragment,container,false);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topPagrActionListener != null){
                    topPagrActionListener.onTopPageAction();
                }
            }
        });
        return view;
    }

    public String TAG = "DemoFragment";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("bensaku", TAG+"onViewCreated");

    }

    public DemoFragment(){
        super(R.layout.demo_fragment);
        TAG = TAG +" "+ UUID.randomUUID().toString().substring(0,4)+" ";
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TopPageActionListener){
            topPagrActionListener = (TopPageActionListener) context;
        }
        Log.d("bensaku", TAG+"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("bensaku", TAG+"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("bensaku", TAG+ "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("bensaku", TAG+ "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("bensaku", TAG+"onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("bensaku", TAG+ "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("bensaku", TAG+ "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("bensaku", TAG+ "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        topPagrActionListener = null;
        Log.d("bensaku", TAG+ "onDetach");
    }
}
