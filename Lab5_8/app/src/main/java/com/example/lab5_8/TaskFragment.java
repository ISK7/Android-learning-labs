package com.example.lab5_8;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {
    private EditText data, description;
    private String dataStr = "", descrStr = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment, container, false);

        data = view.findViewById(R.id.data);
        description = view.findViewById(R.id.description);
        data.setText(dataStr);
        description.setText(descrStr);

        return view;
    }
    public void setTask(Task t) {
        dataStr = t.getDate();
        descrStr = t.getDescription();
        if(data != null && description != null) {
            data.setText(t.getDate());
            description.setText(t.getDescription());
        }
    }
}
