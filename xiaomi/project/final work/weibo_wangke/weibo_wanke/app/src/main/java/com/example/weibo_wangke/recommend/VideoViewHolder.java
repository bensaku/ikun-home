package com.example.weibo_wangke.recommend;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.weibo_wangke.R;

public class VideoViewHolder extends BaseViewHolder {
    public boolean isVideoPlaying = false;
    public SeekBar videoSeekBar;
    public VideoView videoView;
    public ImageView coverImage;

    public VideoViewHolder(View view) {
        super(view);
        videoView = view.findViewById(R.id.video_view);
        videoSeekBar = view.findViewById(R.id.video_seek_bar);
        coverImage=view.findViewById(R.id.cover_image);
    }
}

