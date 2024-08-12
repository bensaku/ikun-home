package com.example.weibo_wangke.recommend;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.example.weibo_wangke.R;
import com.example.weibo_wangke.bean.WeiboInfoBean;

import java.util.List;


public class RecommendRecycleAdapter extends BaseQuickAdapter<WeiboInfoBean, VideoViewHolder> implements LoadMoreModule {

    private static final String TAG = "RecommendRecycleAdapter";

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public RecommendRecycleAdapter(int layoutResId, @Nullable List<WeiboInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull VideoViewHolder helper, WeiboInfoBean item) {
        setContent(helper, item);
        dependType(helper, item);

        // 检查视频是否在播放，如果没有播放则显示封面
        if (!helper.isVideoPlaying) {
            helper.coverImage.setVisibility(View.VISIBLE);
            helper.videoView.setVisibility(View.GONE);
            helper.videoSeekBar.setVisibility(View.GONE);
        }
        ImageView deleteButton = helper.findView(R.id.iv_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position);
                }
            }
        });


        //评论
        View ll_comment = helper.getView(R.id.ll_comment);
        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getBindingAdapterPosition();
                Toast.makeText(getContext(), "点击了第条" + (position + 1) + "数据评论", Toast.LENGTH_SHORT).show();
            }
        });

        View like = helper.getView(R.id.like_parent);

        //点赞
        ImageView iconImageView = helper.getView(R.id.iv_like);
        like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = helper.getBindingAdapterPosition();
                if(iconImageView.isSelected()){
                    dislikeAnime(iconImageView);
//                    iconImageView.setSelected(false);
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onDislikeClick(position);
                    }
                }else {
                    likeAnime(iconImageView);
//                    iconImageView.setSelected(true);
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onLikeClick(position);
                    }
                }
            }
        });
    }

    private void dislikeAnime(ImageView iconImageView){
        // 缩小动画
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 0.8f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 0.8f, 1.0f);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(iconImageView, scaleX, scaleY);
        scaleAnimator.setDuration(1000);
        scaleAnimator.start();
    }

    private void likeAnime(ImageView iconImageView){
        // 放大、缩小动画
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.2f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.2f, 1.0f);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(iconImageView, scaleX, scaleY);
        scaleAnimator.setDuration(1000);
        //旋转动画
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(iconImageView, View.ROTATION_Y, 0f, 360f);
        rotationAnimator.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnimator, rotationAnimator);
        animatorSet.start();
    }

    public void dependType(VideoViewHolder helper, WeiboInfoBean item) {
        Log.i(TAG, item.username);
        setVisible(helper);
        if (item.videoUrl != null && !item.videoUrl.isEmpty()) {
            setVideo(helper, item);
        } else {
            if (item.images != null && !item.images.isEmpty()) {
                if (item.images.size() == 1) {
                    // 调用方法并处理回调
                    ImageUtils.isImageHorizontal(getContext(), item.images.get(0), isHorizontal -> {
                        if (isHorizontal) {
                            // 处理横向图片
                            setSingleH(helper, item);
                        } else {
                            // 处理纵向图片
                            setSingleV(helper, item);
                        }
                    });
                } else setNine(helper, item);
            } else {
                setVisible(helper);
            }
        }
    }

    private void setVisible(VideoViewHolder helper) {
        View videoParent = helper.getView(R.id.video_parent);
        videoParent.setVisibility(View.GONE);
        View include = helper.getView(R.id.nine_picture);
        include.setVisibility(View.GONE);
        ImageView imageViewV = helper.findView(R.id.single_vertical);
        assert imageViewV != null;
        imageViewV.setVisibility(View.GONE);
        ImageView imageViewH = helper.getView(R.id.single_horizontal);
        imageViewH.setVisibility(View.GONE);
    }

    private void setContent(VideoViewHolder helper, WeiboInfoBean item) {
        ImageView avatar = helper.getView(R.id.iv_avatar);
        Glide.with(getContext()).load(item.avatar).apply(RequestOptions.circleCropTransform()).into(avatar);
        TextView username = helper.getView(R.id.tv_username);
        username.setText(item.username);
        TextView title = helper.getView(R.id.tv_weibo_title);
        title.setText(item.title);
        ImageView like = helper.getView(R.id.iv_like);
        like.setSelected(item.likeFlag);
    }
    private void setVideo(VideoViewHolder helper, WeiboInfoBean item) {
        View videoParent = helper.getView(R.id.video_parent);
        videoParent.setVisibility(View.VISIBLE);
        VideoView videoView = helper.videoView;
        videoView.setVisibility(View.INVISIBLE);
        SeekBar videoSeekBar = helper.videoSeekBar;
        ImageView coverImage = helper.coverImage;
        coverImage.setVisibility(View.VISIBLE);
        Glide.with(getContext())
                .load(item.poster)
                .transform(new PlayIconTransformation(getContext(), R.drawable.play_icon)) // 添加播放图标
                .into(coverImage);
        Uri uri = Uri.parse(item.videoUrl);
        videoView.setVideoURI(uri);

        coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coverImage.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                videoSeekBar.setVisibility(View.VISIBLE);
                videoView.start();
                helper.isVideoPlaying = true;
                updateSeekBar(helper);
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.isVideoPlaying) {
                    videoView.pause();
                } else {
                    videoView.start();
                    updateSeekBar(helper);
                }
                helper.isVideoPlaying = !helper.isVideoPlaying;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoSeekBar.setMax(videoView.getDuration()); // 设置 SeekBar 的最大值为视频的总时长
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                coverImage.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                helper.isVideoPlaying = false;
                videoSeekBar.setVisibility(View.GONE);
                videoSeekBar.setProgress(0);
            }
        });
    }
    private void updateSeekBar(VideoViewHolder holder) {
        holder.videoSeekBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (holder.isVideoPlaying) {
                    holder.videoSeekBar.setProgress(holder.videoView.getCurrentPosition());
                    updateSeekBar(holder);
                }
            }
        }, 1000); // 每秒更新一次
    }
    private void setNine(VideoViewHolder helper, WeiboInfoBean item) {
        View include = helper.getView(R.id.nine_picture);
        include.setVisibility(View.VISIBLE);
        List<String> images = item.images;
        int loopCount = Math.min(images.size(), 9);
        if (images.size() <= 9) {
            for (int i = 1; i <= images.size(); i++) {
                int imageResId = getContext().getResources().getIdentifier("picture_" + i, "id", getContext().getPackageName());
                ImageView imageView = helper.getView(imageResId);
                Glide.with(getContext()).
                        load(images.get(i - 1))
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(imageView);
                int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = helper.getBindingAdapterPosition();
                        listener.onOpenImage(position, finalI - 1);
                    }
                });
            }
            for (int i = 1; i <= 9 - images.size(); i++) {
                int imageResId = getContext().getResources().getIdentifier("picture_" + (10 - i), "id", getContext().getPackageName());
                ImageView imageView = helper.getView(imageResId);
                Glide.with(getContext()).
                        load(R.drawable.empty_img)
                        .apply(new RequestOptions().transform(new CenterCrop()))
                        .into(imageView);
            }
        }

        for (int i = 1; i <= loopCount; i++) {

        }
    }
    private void setSingleV(VideoViewHolder helper, WeiboInfoBean item) {
        ImageView imageView = helper.findView(R.id.single_vertical);
        imageView.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(item.images.get(0)).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getBindingAdapterPosition();
                listener.onOpenImage(position,0);
            }
        });
    }
    private void setSingleH(VideoViewHolder helper, WeiboInfoBean item) {
        ImageView imageView = helper.getView(R.id.single_horizontal);
        imageView.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(item.images.get(0)).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getBindingAdapterPosition();
                listener.onOpenImage(position,0);
            }
        });
    }
    public void updateAdapter(List<WeiboInfoBean> newData) {
        setNewInstance(newData);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (!payloads.isEmpty()) {
            // 仅更新特定部分
            for (Object payload : payloads) {
                if (payload instanceof Boolean) {
                    boolean isSelected = (boolean) payload;
                    ImageView image = holder.findView(R.id.iv_like);
                    image.setSelected(isSelected); // 更新 ImageView 的状态
                }
            }
        }
    }


    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onLikeClick(int position);

        void onDislikeClick(int position);

        void onOpenImage(int position,int imgPosition);
    }

}
