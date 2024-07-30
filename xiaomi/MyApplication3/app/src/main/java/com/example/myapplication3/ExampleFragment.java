package com.example.myapplication3;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExampleFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int someInt = requireArguments().getInt("some_int");
        System.out.println(someInt);
    }

    public ExampleFragment(){
        super(R.layout.example_fragment);
    }
}
