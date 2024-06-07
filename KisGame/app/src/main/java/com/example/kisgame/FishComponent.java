package com.example.kisgame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;


public class FishComponent extends ConstraintLayout {

    private int speed;
    ImageView fish;

    public FishComponent(Context context) {
        super(context);
        init(context,null, 0);
    }

    public FishComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs, 0);
    }

    public FishComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = LayoutInflater.from(context);
        speed = Resources.getJumpSpeed();
        inflater.inflate(R.layout.sample_fish_component, this, true);
        fish = findViewById(R.id.body);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void resetRotation() {
        fish.setRotation(Math.min(2*speed,45));
    }
}