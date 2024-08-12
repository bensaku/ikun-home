package com.example.myapplication.work0530;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0530_detail_main_activity);

        // 获取传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("imageUrl")) {
                // 如果是图片数据，设置图片到 ImageView
                String imageUrl = intent.getStringExtra("imageUrl");
                System.out.println(imageUrl);
                setImage(imageUrl);
            } else if (intent.hasExtra("text")) {
                // 如果是文本数据，设置文本到 TextView
                String text = intent.getStringExtra("text");
                System.out.println(text);
                setText(text);
            }
        }
    }

    private void setImage(String imageUrl) {
        ImageView imageView = findViewById(R.id.work0530_detail_image);
        int resId = Integer.parseInt(imageUrl);
        Glide.with(this).load(resId).into(imageView);
    }

    private void setText(String text) {
        TextView textView = findViewById(R.id.work0530_detail_text);
        textView.setText(text);
    }


}
