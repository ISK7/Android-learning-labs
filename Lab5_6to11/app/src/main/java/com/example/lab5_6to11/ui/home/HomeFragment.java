package com.example.lab5_6to11.ui.home;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_6to11.R;
import com.example.lab5_6to11.databinding.FragmentHomeBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class HomeFragment extends Fragment {

    ArrayList<State> states = new ArrayList<State>();
    TaskAdapter adapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.task_list);
        adapter = new TaskAdapter(this.getContext(), states);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Button addBut = root.findViewById(R.id.task_button);
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTaskDialog();
            }
        });

        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void newState(String data, String description, int d, int m, int y) {
        State state = new State(data, description, d, m, y);
        for(int i = 0; i < states.size(); i++) {
            if(states.get(i).date.equals(state.date)) {
                State h = states.get(i);
                h.addTask(description);
                states.set(i, h);
                adapter.notifyItemChanged(i);
                return;
            }
        }
        states.add(state);
        adapter.notifyItemInserted(states.size()-1);
        Collections.sort(states);
        for(int i = 0; i < states.size(); i++) {
            adapter.notifyItemChanged(i);
        }
    }

    private void OpenTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter task");

        View dialogView = getLayoutInflater().inflate(R.layout.add_task_dialog, null);
        builder.setView(dialogView);

        DatePicker dp = dialogView.findViewById(R.id.task_d);
        EditText et = dialogView.findViewById(R.id.task_t);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dateStr = GetDate(dp);
                String textStr = et.getText().toString();
                int d = dp.getDayOfMonth();
                int m = dp.getMonth();
                int y = dp.getYear();
                newState(dateStr,textStr, d, m, y);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String GetDate(DatePicker dp) {
        return dp.getDayOfMonth() + "." + dp.getMonth() + "." + dp.getYear();
    }
}
