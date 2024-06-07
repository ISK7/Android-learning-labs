package com.example.kisgame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class PickUp extends ConstraintLayout {

    private Operation operation;
    private int value;
    TextView text;

    public PickUp(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pick_up_component, this, true);

        operation = Operation.ADDITION;
        value = 1;
        text = findViewById(R.id.bonus);
        text.setScaleY((float) 0.3);
        setText();
    }

    public void setColorNum(int buttonColor) {
        ImageView imageView = findViewById(R.id.pick_up);
        switch (buttonColor) {
            case 1 : {
                imageView.setImageResource(R.drawable.pickup1);
                break;
            }
            case 2 : {
                imageView.setImageResource(R.drawable.pickup2);
                break;
            }
            case 3 : {
                imageView.setImageResource(R.drawable.pickup3);
                break;
            }
            case 4 : {
                imageView.setImageResource(R.drawable.pickup4);
                break;
            }
        }
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    public Operation getOperation() {return operation;}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setText();
    }

    private void setText() {
        switch (operation) {
            case ADDITION: {
                text.setText(String.format(Locale.ENGLISH,"+%d",value));
                break;
            }
            case DIVISION: {
                text.setText(String.format(Locale.ENGLISH,"%%%d",value));
                break;
            }
            case SUBTRACTION: {
                text.setText(String.format(Locale.ENGLISH,"-%d",value));
                break;
            }
            case MULTIPLICATION: {
                text.setText(String.format(Locale.ENGLISH,"*%d",value));
                break;
            }
            default: {
                text.setText(String.format(Locale.ENGLISH,"+%d",1));
                break;
            }
        }
    }
}
