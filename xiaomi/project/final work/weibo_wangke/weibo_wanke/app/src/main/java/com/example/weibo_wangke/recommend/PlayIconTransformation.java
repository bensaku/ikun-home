package com.example.weibo_wangke.recommend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

public class PlayIconTransformation extends BitmapTransformation {
    private final Drawable playIcon;

    public PlayIconTransformation(Context context, int playIconResId) {
        playIcon = ContextCompat.getDrawable(context, playIconResId);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), toTransform.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(toTransform, 0, 0, null);

        if (playIcon != null) {
            int left = (toTransform.getWidth() - playIcon.getIntrinsicWidth()) / 2;
            int top = (toTransform.getHeight() - playIcon.getIntrinsicHeight()) / 2;
            playIcon.setBounds(left, top, left + playIcon.getIntrinsicWidth(), top + playIcon.getIntrinsicHeight());
            playIcon.draw(canvas);
        }

        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update("PlayIconTransformation".getBytes());
    }
}

