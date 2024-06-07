package com.example.lab9.ui.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.lab9.R;

public class CanvasView extends View {
    Canvas canvas;
    public CanvasView(Context context) {
        super(context);
    }
    public CanvasView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        canvas = new Canvas();
    }
    @Override
    public void draw(Canvas newCanvas) {
        super.draw(newCanvas);
    }

    @Override
    protected void onDraw(Canvas newCanvas) {
        super.onDraw(newCanvas);
        Paint teal = new Paint();
        teal.setColor(getResources().getColor(R.color.teal_200));
        Paint green = new Paint();
        green.setColor(Color.GREEN);
        Paint yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        Paint red = new Paint();
        red.setColor(Color.RED);
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        int width = this.getWidth();
        int height = this.getHeight();
        newCanvas.drawRect(0,0,width,height/2,teal);
        newCanvas.drawRect(0,height/2,width,height,green);
        newCanvas.drawRect(width/2-400,height/2-400,width/2+400,height/2+400,yellow);
        newCanvas.drawRect(width/2+360,height/2-600,width/2+400,height/2-400,black);
        Path path = new Path();
        path.moveTo(width/2-480,height/2-400);
        path.lineTo(width/2,height/2-600);
        path.lineTo(width/2+480,height/2-400);
        path.lineTo(width/2-480,height/2-400);
        path.close();
        newCanvas.drawPath(path,red);
        canvas = newCanvas;
    }
}
