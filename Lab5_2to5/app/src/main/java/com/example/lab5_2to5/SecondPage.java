package com.example.lab5_2to5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SecondPage extends Fragment {
    private FragmentManager fragmentManager;
    private TextView text;
    private TextView data;
    private TextView time;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.seond_page, container, false);

        fragmentManager = requireActivity().getSupportFragmentManager();
        text = view.findViewById(R.id.text_view);
        data = view.findViewById(R.id.data_view);
        time = view.findViewById(R.id.time_view);

        Button backButton = view.findViewById(R.id.back_button);
        Button textButton = view.findViewById(R.id.text_button);
        Button dataButton = view.findViewById(R.id.data_button);
        Button timeButton = view.findViewById(R.id.time_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack();
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTextDialog();
            }
        });
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDataDialog();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTimeDialog();
            }
        });
        return view;
    }

    private void OpenTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter time");

        View dialogView = getLayoutInflater().inflate(R.layout.time_input_dialog, null);
        builder.setView(dialogView);

        TimePicker tPicker = dialogView.findViewById(R.id.time_p);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TextStr = GetTime(tPicker);

                time.setText(TextStr);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void OpenDataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose data");

        View dialogView = getLayoutInflater().inflate(R.layout.data_input_dialog, null);
        builder.setView(dialogView);

        DatePicker dPicker = dialogView.findViewById(R.id.date_p);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TextStr = GetDate(dPicker);

                data.setText(TextStr);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void OpenTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter text");

        View dialogView = getLayoutInflater().inflate(R.layout.text_input_dialog, null);
        builder.setView(dialogView);

        EditText eText = dialogView.findViewById(R.id.text_edit);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TextStr = eText.getText().toString();

                text.setText(TextStr);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private String GetDate(DatePicker dp) {
        return dp.getDayOfMonth() + "." + dp.getMonth() + "." + dp.getYear();
    }
    private String GetTime(TimePicker tp) {
        return tp.getHour() + ":" + tp.getMinute();
    }
}
