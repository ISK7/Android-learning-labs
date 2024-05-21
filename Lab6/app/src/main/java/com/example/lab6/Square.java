package com.example.lab6;

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

    private String text = "Placeholder";
    private Paint squareText;

    public Square(Context context) {
        super(context);
        init();
    }

    public Square(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Square);
        text = a.getString(R.styleable.Square_squareText);
        squareColor = a.getColor(R.styleable.Square_squareColor, Color.RED);
        a.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        squareText = new Paint();
        squareText.setColor(Color.WHITE);
        squareText.setTextSize(50);
        squareText.setTextAlign(Paint.Align.CENTER);
    }

    public void setSquareColor(int color) {
        squareColor = color;
        invalidate();
    }

    public void setSquareText(String txt) {
        text = txt;
        invalidate();
    }
    public void setSquareTextFromAttributes(TypedArray attributes) {
        String txt = attributes.getString(R.styleable.Square_squareText);
        if (txt != null) {
            text = txt;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = Math.min(canvas.getWidth(), canvas.getHeight());
        int left = (canvas.getWidth() - size) / 2;
        int top = (canvas.getHeight() - size) / 2;
        int right = left + size;
        int bottom = top + size;
        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;


        paint.setColor(squareColor);

        canvas.drawRect(left, top, right, bottom, paint);
        canvas.drawText(text, viewWidthHalf, viewHeightHalf, squareText);
    }
}
