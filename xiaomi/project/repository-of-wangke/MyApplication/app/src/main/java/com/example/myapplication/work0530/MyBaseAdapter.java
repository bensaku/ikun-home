package com.example.myapplication.work0530;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyBaseAdapter extends BaseMultiItemQuickAdapter<Item, BaseViewHolder> {

    public MyBaseAdapter(List<Item> data) {
        super(data);
        addItemType(Item.TYPE_IMAGE, R.layout.work0530_item_image);
        addItemType(Item.TYPE_TEXT, R.layout.work0530_item_text);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, Item item) {
        switch (holder.getItemViewType()) {
            case Item.TYPE_IMAGE:
                // 图片
                ImageView imageView = holder.getView(R.id.work0530_image_view);
                int resId = Integer.parseInt(item.getContent());
                Glide.with(getContext())
                        .load(resId)
                        .into(imageView);


                //监听图片的点击
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post("image|" + item.getContent());
                    }
                });
                break;
            case Item.TYPE_TEXT:
                // 文本
                holder.setText(R.id.work0530_text_view, item.getContent());

                //监听文本的点击
                TextView textView = holder.getView(R.id.work0530_text_view);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post("text|" + item.getContent());
                    }
                });
                break;
        }
    }

    //更新数据
    public void updateData(List<Item> newData) {
        setNewInstance(newData);
        notifyDataSetChanged();
    }

}


