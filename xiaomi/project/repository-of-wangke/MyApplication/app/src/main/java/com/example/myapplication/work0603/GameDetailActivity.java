package com.example.myapplication.work0603;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.bean.GameBean;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GameDetailActivity extends AppCompatActivity {

    private static final String EXTRA_GAME = "extra_game";

    public static Intent newIntent(Context context, GameBean gameBean) {
        Intent intent = new Intent(context, GameDetailActivity.class);
        intent.putExtra(EXTRA_GAME, (Serializable) gameBean);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0603_activity_gamedetail);

        GameBean gameBean = (GameBean) getIntent().getSerializableExtra(EXTRA_GAME);

        if(gameBean!=null){
            ImageView icon = findViewById(R.id.work0603_game_icon);
            TextView name = findViewById(R.id.work0603_game_name);
            TextView introduction = findViewById(R.id.work0603_game_introduction);
            TextView score = findViewById(R.id.work0603_game_score);

            RequestOptions requestOptions = new RequestOptions()
                    .transform(new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL));
            Glide.with(this)
                    .load(gameBean.getIcon())
                    .apply(requestOptions)
                    .into(icon);
            name.setText(gameBean.getGameName());
            introduction.setText(gameBean.getIntroduction());
            score.setText(String.valueOf(gameBean.getScore()));

            TextView button = findViewById(R.id.work0603_game_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post("114514");
                }
            });
        }
    }
}
