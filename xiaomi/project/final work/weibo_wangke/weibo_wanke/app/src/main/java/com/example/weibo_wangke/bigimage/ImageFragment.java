package com.example.weibo_wangke.bigimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.weibo_wangke.R;

public class ImageFragment extends Fragment {

    private static final String ARG_IMAGE_URL = "imageUrl";

    private String imageUrl;

    public static ImageFragment newInstance(String imageUrl) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            imageUrl = getArguments().getString(ARG_IMAGE_URL);
        }

        if (imageUrl != null) {
            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imageView);
        }

        return view;
    }
}
