package com.example.myapplication.work0531;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MainActivity extends Activity {

    private static final String TAG = "Animation";
    private int alphaRepeatCount = 0;
    private int scaleRepeatCount = 0;
    private int RotateRepeatCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0531_activity_main);

        ImageView imageView1 = findViewById(R.id.work0531_anime_view1);

        //作业一
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setRepeatCount(3);

        RotateAnimation rotateAnimation = new RotateAnimation(0, -720, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(3);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.8f);
        alphaAnimation.setRepeatCount(3);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(2000);

        //监听透明动画
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "alpha animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "alpha animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                alphaRepeatCount++;
                Log.d(TAG, "alpha animation repeat");
                Log.d(TAG, "alpha animation 重复了" + alphaRepeatCount + "次");
            }
        });

        // 监听缩放动画
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "Scale animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "Scale animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                scaleRepeatCount++;
                Log.d(TAG, "Scale animation repeat");
                Log.d(TAG, "Scale animation 重复了" + scaleRepeatCount + "次");
            }
        });

// 监听旋转动画
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "Rotate animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "Rotate animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                RotateRepeatCount++;
                Log.d(TAG, "Rotate animation repeat");
                Log.d(TAG, "Rotate animation 重复了" + RotateRepeatCount + "次");
            }
        });


        imageView1.startAnimation(animationSet);

        //作业二
        //x轴旋转
        ImageView imageView2 = findViewById(R.id.work0531_anime_view2);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(imageView2, View.ROTATION_X, 0, 360);
        rotationAnimator.setDuration(1000);
        //右移
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_X, 0, 120);
        translationAnimator.setInterpolator(new CustomTimeInterpolator());
        translationAnimator.setDuration(1000);
        //透明
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView2, "alpha", 1f, 0.5f);
        alphaAnimator.setEvaluator(new CustomTypeEvaluator());
        alphaAnimator.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet togetherSet = new AnimatorSet();
        togetherSet.playTogether(rotationAnimator, translationAnimator);
        animatorSet.play(togetherSet).before(alphaAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();

    }

    //插值器
    public static class CustomTimeInterpolator implements TimeInterpolator {

        public float getInterpolation(float input) {
            if (input < 0.3535f) return bounce(input);
            else if (input < 0.7408f) return bounce(input - 0.54719f) + 0.7f;
            else if (input < 0.9644f) return bounce(input - 0.8526f) + 0.9f;
            else return bounce(input - 1.0435f) + 0.95f;
        }

        private float bounce(float t) {
            return t * t * 8.0f;
        }
    }

    //估值器
    public static class CustomTypeEvaluator implements TypeEvaluator<Float> {
        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            if (fraction < 0.5) {
                return startValue;
            } else
                return startValue + fraction * (endValue - startValue);
        }
    }
}
