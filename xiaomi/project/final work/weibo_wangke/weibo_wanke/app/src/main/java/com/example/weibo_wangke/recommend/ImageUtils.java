package com.example.weibo_wangke.recommend;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import com.example.weibo_wangke.recommend.ImageOrientationCallback;

public class ImageUtils {

    public static void isImageHorizontal(Context context, String imageUrl, ImageOrientationCallback callback) {
        new Thread(() -> {
            boolean isHorizontal = true;
            try {
                Bitmap bitmap = Glide.with(context)
                        .asBitmap()
                        .load(imageUrl)
                        .submit()
                        .get(); // 同步获取Bitmap对象
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                isHorizontal = width > height; // 如果宽度大于高度，则是横向
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                isHorizontal = true; // 在异常情况下，默认为横向
            }

            // 在主线程上执行回调
            boolean finalIsHorizontal = isHorizontal;
            new Handler(Looper.getMainLooper()).post(() -> callback.onResult(finalIsHorizontal));
        }).start();
    }
}

