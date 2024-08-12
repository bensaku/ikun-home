package com.example.myapplication.work0603;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.bean.GameBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GameDetailRecycleAdapter extends BaseQuickAdapter<GameBean, BaseViewHolder> implements LoadMoreModule {


    public GameDetailRecycleAdapter(int layoutResId, @Nullable List<GameBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, GameBean gameBean) {
        // icon
        ImageView imageView = holder.getView(R.id.work0528_game_icon);
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL));
        Glide.with(getContext())
                .load(gameBean.getIcon())
                .apply(requestOptions)
                .into(imageView);

        //gameName
        TextView gameName = holder.getView(R.id.work0528_game_name);
        gameName.setText(gameBean.getGameName());

        //gameIntroduction
        TextView gameIntroduction = holder.getView(R.id.work0528_game_introduction);
        gameIntroduction.setText(gameBean.getIntroduction());

        //gameScore
        TextView gameScore =holder.getView(R.id.work0528_game_score);
        gameScore.setText(gameBean.getScore().toString());

    }


    //更新数据
    public void updateData(List<GameBean> newData) {
        setNewInstance(newData);
        notifyDataSetChanged();
    }

}


