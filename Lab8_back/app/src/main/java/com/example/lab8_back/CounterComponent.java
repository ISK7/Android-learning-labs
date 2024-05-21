package com.example.lab8_back;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab8_back.R;

import java.util.jar.Attributes;

public class CounterComponent extends LinearLayout {
    Integer num = 0;
    TextView counter;
    Button add;
    Button reset;
    public CounterComponent(Context context, AttributeSet attr) {
        super(context);
        init(context,attr);
    }

    private void init(Context context, AttributeSet attr) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.counter_component, this, true);

        add = findViewById(R.id.increase_button);
        counter = findViewById(R.id.count_text);
        reset = findViewById(R.id.reset_button);

        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.CounterFragment);
        num = a.getInt(R.styleable.CounterFragment_count, 0);
        counter.setText(num.toString());

        add.setOnClickListener(v -> onIncreaseClick());
        reset.setOnClickListener(v -> onResetClick());
    }

    private void onIncreaseClick() {
        num++;
        counter.setText(num.toString());
    }
    private void onResetClick() {
        num = 0;
        counter.setText(num.toString());
    }
}
