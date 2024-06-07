package com.example.lab9.ui.fountain;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class Image {
    ImageView imageView;
    int angle, vertical, timeToLive;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    public void decreaseVertical() {
        vertical -= Resourses.getFallSpeed();
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getTimeToLive() {
        return timeToLive;
    }
    public void decreaseTimeToLive() {
        timeToLive -= 1;
        if(timeToLive <=0) {
            ViewParent parent = imageView.getParent();
            if(parent instanceof ViewGroup)
                ((ViewGroup)parent).removeView(imageView);
        }
    }
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void moveParticle() {
        imageView.setX(imageView.getX() + angle);
        imageView.setY(imageView.getY() - vertical);
        decreaseVertical();
        decreaseTimeToLive();
        imageView.invalidate();
    }
}
