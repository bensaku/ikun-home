package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import java.util.Random;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {

    private final String[] colors = new String[]{
            "#FFFFEB3B",
            "#FFFFB6C1",
            "#FF87CEFA",
            "#CC7722",
            "#FFE0FFFF",
            "#FF00BFFF"
    };

    private int index;
    private Paint paint;
    private RectF rectF = new RectF();

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private  void  init(){
        index = new Random().nextInt(colors.length);
        paint = new Paint();
        paint.setColor(Color.parseColor(colors[index]));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectF.set(0,0,getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(rectF,15,15,paint);
        super.onDraw(canvas);
    }
}
