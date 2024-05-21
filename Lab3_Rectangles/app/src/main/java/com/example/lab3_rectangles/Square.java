package com.example.lab3_rectangles;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Square extends View {

    private Paint paint;
    private int squareColor;

    public Square(Context context) {
        super(context);
        init();
    }

    public Square(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Square);
        squareColor = a.getColor(R.styleable.Square_squareColor, Color.RED);
        a.recycle();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    public void setSquareColor(int color) {
        squareColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = Math.min(canvas.getWidth(), canvas.getHeight());
        int left = (canvas.getWidth() - size) / 2;
        int top = (canvas.getHeight() - size) / 2;
        int right = left + size;
        int bottom = top + size;

        paint.setColor(squareColor);
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
