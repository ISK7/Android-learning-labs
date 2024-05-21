package com.example.lab5_2to5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FirstPage extends Fragment {

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_page, container, false);

        fragmentManager = requireActivity().getSupportFragmentManager();

        Button createButton = view.findViewById(R.id.second_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondFragment();
            }
        });

        Button deleteButton = view.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSecondFragment();
            }
        });
        return view;
    }

    private void showSecondFragment() {
        SecondPage sp = new SecondPage();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, sp, "SecondFragment")
                .addToBackStack(null)
                .commit();
    }

    private void deleteSecondFragment() {
        Fragment fragment = fragmentManager.findFragmentByTag("SecondFragment");
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }
}
