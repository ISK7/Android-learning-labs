package com.example.lab5_6to11.ui.home;


import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class State implements Comparable<State> {
    LocalDate date;
    String  dataView, textView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public State(String data, String description, int d, int m, int y) {
        dataView = data;
        textView = description;
        date = LocalDate.of(y,m,d);

    }

    public String getDataView() {
        return dataView;
    }
    public String getTextView() {
        return textView;
    }
    public void addTask(String str) {
        textView += "\n" + str;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(State other) {
        return this.date.compareTo(other.date);
    }
}
