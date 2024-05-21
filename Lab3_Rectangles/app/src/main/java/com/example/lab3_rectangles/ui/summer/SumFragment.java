package com.example.lab3_rectangles.ui.summer;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab3_rectangles.MainActivity;
import com.example.lab3_rectangles.R;
import com.example.lab3_rectangles.databinding.Fragment5Binding;
import com.example.lab3_rectangles.databinding.FragmentSumBinding;

public class SumFragment extends Fragment {

    private FragmentSumBinding binding;

    public static SumFragment newInstance() {
        return new SumFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSumBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Button d_but = view.findViewById(R.id.dialog_opener);

        d_but.setOnClickListener(v -> {
            openInputDialog();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Input Numbers");

        View dialogView = getLayoutInflater().inflate(R.layout.fragment_sum_dialog, null);
        builder.setView(dialogView);

        EditText firstEditText = dialogView.findViewById(R.id.first_label);
        EditText secondEditText = dialogView.findViewById(R.id.second_label);

        builder.setPositiveButton("Sum", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String firstNumberStr = firstEditText.getText().toString();
                String secondNumberStr = secondEditText.getText().toString();

                int firstNumber = Integer.parseInt(firstNumberStr);
                int secondNumber = Integer.parseInt(secondNumberStr);

                int sum = firstNumber + secondNumber;
                Toast.makeText(getContext(), "Sum: " + sum, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}