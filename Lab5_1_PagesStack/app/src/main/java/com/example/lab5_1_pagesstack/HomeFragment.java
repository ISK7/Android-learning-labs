package com.example.lab5_1_pagesstack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeFragment extends Fragment {

    private Button nextButton;
    private Button previousButton;
    public TextView stackCount;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        stackCount = view.findViewById(R.id.stack_view);
        nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });

        previousButton = view.findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = fragmentManager.getBackStackEntryCount();
                if(size > 1)
                    fragmentManager.popBackStack();
            }
        });
        stackCount.setText("Stack depth: " + fragmentManager.getBackStackEntryCount());
        return view;
    }
}