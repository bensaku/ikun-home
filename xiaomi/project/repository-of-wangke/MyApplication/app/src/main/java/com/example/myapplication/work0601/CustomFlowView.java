package com.example.myapplication.work0601;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class CustomFlowView extends ViewGroup {
    private int horizontalSpace = 0;
    private int verticalSpace = 0;

    public CustomFlowView(Context context) {
        super(context);
    }

    public CustomFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFlowView);
        horizontalSpace = (int) typedArray.getDimension(R.styleable.CustomFlowView_horizontalSpace, 10);
        verticalSpace = (int) typedArray.getDimension(R.styleable.CustomFlowView_verticalSpace, 10);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int usedWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight() - getPaddingLeft();
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        int lineWidth = 0;
        int curLineHeight = 0;
        int parentHeight = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //传入父view的测量模式去测量子view宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childW = child.getMeasuredWidth();
            int childH = child.getMeasuredHeight();

            if (childW + lineWidth <= usedWidth) {
                lineWidth = lineWidth + childW + horizontalSpace;
                curLineHeight = Math.max(childH, curLineHeight);
            } else {
                //重置为换行的子view的宽度
                lineWidth = childW + horizontalSpace;
                parentHeight = parentHeight + curLineHeight + verticalSpace;
                curLineHeight = childH;
            }

        }
        parentHeight += curLineHeight;
        //重新设置父view宽高
        if (mode == MeasureSpec.EXACTLY) {
            parentHeight = sizeH;
        }
        setMeasuredDimension(usedWidth + getPaddingLeft()+getPaddingRight(), parentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int used = r - l - getPaddingRight();

        int left = getPaddingLeft();
        int top = getPaddingTop();

        int curLineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childW = child.getMeasuredWidth();
            int childH = child.getMeasuredHeight();

            if (left + childW <= used) {
                curLineHeight = Math.max(childH, curLineHeight);
            } else {
                left = getPaddingLeft();
                top = top + curLineHeight + verticalSpace;
                curLineHeight = childH;
            }

            child.layout(left, top, childW + left, childH + top);

            left = left + childW + horizontalSpace;
        }

    }

}
