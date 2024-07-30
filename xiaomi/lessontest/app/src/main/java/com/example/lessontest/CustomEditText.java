package com.example.lessontest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {


    public Paint paint;
    public Rect rect;


    public void setPaint() {
        rect = new Rect();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
    }

    public CustomEditText(Context context) {
        super(context);
        setPaint();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //原有控件绘制
        super.onDraw(canvas);

        //自定义部分
        int lineCount = getLineCount();
        for (int i = 0; i < lineCount; i++) {


            //文字底部
            int baseLine= getLineBounds(i, rect);
            float startX = rect.left;
            float startY = baseLine;
            float stopX =rect.right;
            float stopY =baseLine;
            canvas.drawLine(startX,startY,stopX,stopY,paint);
        }
    }
}
