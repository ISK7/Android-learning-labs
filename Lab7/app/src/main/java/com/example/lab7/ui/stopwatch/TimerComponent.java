package com.example.lab7.ui.stopwatch;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.lab7.R;

public class TimerComponent extends LinearLayout {
    private DigitComponent hoursDigit;
    private DigitComponent minutesDigit;
    private DigitComponent secondsDigit;

    public TimerComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponents(context);
    }

    private void initComponents(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.timer_component, this, true);

        hoursDigit = findViewById(R.id.hoursDigit);
        minutesDigit = findViewById(R.id.minutesDigit);
        secondsDigit = findViewById(R.id.secondsDigit);
    }

    public void setTime(int hours, int minutes, int seconds) {
        hoursDigit.setDigit(hours);
        minutesDigit.setDigit(minutes);
        secondsDigit.setDigit(seconds);
    }
}
