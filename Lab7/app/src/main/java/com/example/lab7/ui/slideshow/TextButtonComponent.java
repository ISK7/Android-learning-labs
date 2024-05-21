package com.example.lab7.ui.slideshow;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.lab7.R;

public class TextButtonComponent extends LinearLayout {
    private Button button;

    public TextButtonComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.text_button_component, this, true);

        button = findViewById(R.id.button);
        button.setText("");

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextButtonComponent);
        int buttonColor = a.getColor(R.styleable.TextButtonComponent_buttonColor, 0);
        a.recycle();

        button.setOnClickListener(v -> onButtonClick());

        button.setBackgroundColor(buttonColor);
    }

    protected void onButtonClick() {
        if (getChildCount() > 1) {
            View childView = getChildAt(1);
            if (childView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childView;
                if (viewGroup.getChildCount() > 1) {
                    View innerView = viewGroup.getChildAt(1);
                    if (innerView instanceof TextView) {
                        TextView tv = (TextView) innerView;
                        button.setText(tv.getText());
                    }
                }
            } else {
                button.setText(((TextView) childView).getText());
            }
        }
    }
}