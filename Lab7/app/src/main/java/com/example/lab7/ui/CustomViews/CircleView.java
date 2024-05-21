package com.example.lab7.ui.CustomViews;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int circleColor = Color.BLACK;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCircleColor(int color) {
        circleColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(circleColor);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int radius = Math.min(centerX, centerY);

        canvas.drawCircle(centerX, centerY, radius, paint);
    }
}
