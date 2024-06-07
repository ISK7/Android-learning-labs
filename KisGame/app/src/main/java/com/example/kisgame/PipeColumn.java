package com.example.kisgame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PipeColumn extends ConstraintLayout {
    Integer hp;
    TextView bar;
    boolean isAlive;
    ImageView image;
    Context cnt;

    public PipeColumn(Context context) {
        super(context);
        init(context,null);
    }

    public PipeColumn(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pipe_column_component, this, true);

        image = findViewById(R.id.pipe);
        cnt = context;
        hp = 10;
        bar = findViewById(R.id.hp);
        bar.setText(hp.toString());
        isAlive = true;
    }

    public boolean setHp(int hp) {
        this.hp = hp;
        return setBarHp();
    }

    public boolean decreaseHp(int damage) {
        hp -= damage;
        return setBarHp();
    }

    private boolean setBarHp() {
        if (hp <= 0) {
            bar.setText("");
            isAlive = false;
            broke();
            return false;
        }
        bar.setText(hp.toString());
        return true;
    }
    public int getHp() {
        return hp;
    }
    public boolean isAlive() {return isAlive;}

    private void broke() {
        bar.setText("");
        AnimatorSet aSet = (AnimatorSet) AnimatorInflater.loadAnimator(cnt,R.animator.broke);
        aSet.setTarget(this);
        aSet.start();
    }
}
