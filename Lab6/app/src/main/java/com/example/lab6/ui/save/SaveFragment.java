package com.example.lab6.ui.save;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab6.R;
import com.example.lab6.databinding.FragmentSaveBinding;

public class SaveFragment extends Fragment {
    private EditText editText;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;

    FragmentSaveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = com.example.lab6.databinding.FragmentSaveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editText = root.findViewById(R.id.save_text);
        checkBox = root.findViewById(R.id.save_box);

        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("text", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("isChecked", false));

        Button button = root.findViewById(R.id.save_button);
        button.setOnClickListener(v -> saveData());

        return root;
    }

    private void saveData() {
        String text = editText.getText().toString();
        boolean isChecked = checkBox.isChecked();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text", text);
        editor.putBoolean("isChecked", isChecked);
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
