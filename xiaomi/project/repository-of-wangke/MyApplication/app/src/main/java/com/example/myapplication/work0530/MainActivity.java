package com.example.myapplication.work0530;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
        if (event.startsWith("image|")) {
            // 提取图片内容并打开新的 Activity
            String imageUrl = event.substring("image|".length());
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("imageUrl", imageUrl);
            startActivity(intent);
        } else if (event.startsWith("text|")) {
            // 提取文本内容并打开新的 Activity
            String text = event.substring("text|".length());
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0530_activity_main);

        //注册
        EventBus.getDefault().register(this);
        //        recyclerView.setAdapter(new MyAdapter());
        TextView buttonMain = findViewById(R.id.work0530_button_main);
        TextView buttonMine = findViewById(R.id.work0530_button_mine);


        MainPageFragment mainPageFragment = new MainPageFragment();
        MineFragment mineFragment = new MineFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.work0530_fragment, mainPageFragment).commit();
        buttonMain.setTextColor(getColor(R.color.blue));


        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.work0530_fragment, mainPageFragment).commit();
                buttonMain.setTextColor(getColor(R.color.blue));
                buttonMine.setTextColor(getColor(R.color.black));
            }
        });

        buttonMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.work0530_fragment, mineFragment).commit();
                buttonMine.setTextColor(getColor(R.color.blue));
                buttonMain.setTextColor(getColor(R.color.black));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册 EventBus
        EventBus.getDefault().unregister(this);
    }
}
