package com.example.lab7.ui.stopwatch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lab7.R;

public class DigitComponent extends View {
    private int digit = 0;
    private int digitColor = Color.BLACK;
    private Paint paint = new Paint();

    public DigitComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DigitComponent, 0, 0);
        try {
            digitColor = a.getColor(R.styleable.DigitComponent_digitColor, Color.BLACK);
        } finally {
            a.recycle();
        }
    }

    public void setDigit(int digit) {
        this.digit = digit;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(digitColor);
        paint.setTextSize(100);
        canvas.drawText(String.valueOf(digit), 0, 100, paint);
    }
}
