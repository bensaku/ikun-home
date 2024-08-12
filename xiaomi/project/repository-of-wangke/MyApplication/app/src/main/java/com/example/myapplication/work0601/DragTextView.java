package com.example.myapplication.work0601;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DragTextView extends TextView {

    private GestureDetector gestureDetecor;

    private float initX;
    private float initY;


    public DragTextView(Context context) {
        super(context);
    }

    public DragTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gestureDetecor = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                initX = e.getRawX();
                initY = e.getRawY();
                return true;
                //true表示处理接下来的事件，false不处理
            }

            @Override
            public void onShowPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
                float rawX = e2.getRawX();
                float rawY = e2.getRawY();
                int delaX = (int) (rawX - initX);
                int delaY = (int) (rawY - initY);
                initX=rawX;
                initY=rawY;

                layout(getLeft() + delaX, getTop() + delaY, getRight() + delaX, getBottom() + delaY);
                return false;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //用户事件交给手势
        gestureDetecor.onTouchEvent(event);
        return true;//返回true消费，不在分发
    }
}
