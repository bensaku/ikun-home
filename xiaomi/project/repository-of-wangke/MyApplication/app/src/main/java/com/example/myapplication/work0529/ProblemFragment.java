package com.example.myapplication.work0529;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class ProblemFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";

    public static ProblemFragment newInstance(String text) {
        ProblemFragment fragment = new ProblemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    public ProblemFragment() {
        super(R.layout.work0529_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work0529_fragment, container, false);
        TextView textView1 = view.findViewById(R.id.work0529_text1);
        TextView textView2 = view.findViewById(R.id.work0529_text2);
        TextView textView3 = view.findViewById(R.id.work0529_text3);
        TextView textView4 = view.findViewById(R.id.work0529_text4);
        if (getArguments() != null) {
            String text = getArguments().getString(ARG_TEXT);
            String[] questions = text.split(";");
            textView1.setText(questions[0]);
            textView2.setText(questions[1]);
            textView3.setText(questions[2]);
            textView4.setText(questions[3]);

        }

        return view;
    }


}
