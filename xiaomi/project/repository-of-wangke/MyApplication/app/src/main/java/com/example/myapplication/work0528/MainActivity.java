package com.example.myapplication.work0528;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private void setData(List<GameBean> data) {
        for (int i = 0; i < 50; i++) {
            int[] icons = new int[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3};
            GameBean game = new GameBean();
            game.setGameIcon(icons[i % 3]);
            game.setGameName("游戏数据" + i);
            data.add(game);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0528_activity_main);

        RecyclerView recyclerView = findViewById(R.id.work0528_recyclerview);
        List<GameBean> data = new ArrayList<>();
        setData(data);
        GameRecycleAdapter adapter = new GameRecycleAdapter(data);
        adapter.setOnItemClickListener(new GameRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = view.getId();
                position+=1;
                // 更显眼的提示信息
                Toast toast = new Toast(MainActivity.this);
                toast.setDuration(Toast.LENGTH_SHORT);


                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(24);
                textView.setTextColor(Color.RED);


                toast.setView(textView);

                if (id == R.id.work0528_game_icon) {
                    textView.setText("点击了icon" + position);
                    toast.show();
                } else if (id == R.id.work0528_game_button) {
                    textView.setText("点击了button" + position);
                    toast.show();
                } else {
                    textView.setText("点击了item" + position);
                    toast.show();
                }


            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //列表项增加删除
        Button addItem = findViewById(R.id.work0528_add_item);
        Button removeItem = findViewById(R.id.work0528_remove_item);
        EditText editText =findViewById(R.id.work0528_edittext);
        GameBean addBean = new GameBean(R.drawable.icon4,"addItem");

        //添加
        addItem.setOnClickListener(v -> {
            if (editText.getText().length()==0) return;
            int position = Integer.parseInt(editText.getText().toString());
            data.add(position,addBean);
            adapter.notifyItemInserted(position);
            adapter.notifyItemRangeChanged(position,data.size());
        });
        //移除
        removeItem.setOnClickListener(v -> {
            if (editText.getText().length()==0) return;
            int position = Integer.parseInt(editText.getText().toString());
            data.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position,data.size());
        });



    }
}