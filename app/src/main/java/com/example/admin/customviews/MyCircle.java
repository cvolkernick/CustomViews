package com.example.admin.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class MyCircle extends View {

    int radius;
    int fillColor;
    private int width;
    private int height;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyCircle(Context context) {
        super(context);
        init(context,null, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs, defStyleAttr, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredHeight = 200;
        int desiredWidth = 200;


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        Log.d(TAG, "onMeasure: width" + width);
        Log.d(TAG, "onMeasure: height" + height);

        setMeasuredDimension(width, height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw: ");

        Paint paint = new Paint();
        paint.setColor(fillColor);

        canvas.drawCircle(width/2, height/2, radius, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircle);

        radius = typedArray.getInt(R.styleable.MyCircle_radius, 10);
        fillColor = typedArray.getColor(R.styleable.MyCircle_color, context.getColor(R.color.colorAccent));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }
}
