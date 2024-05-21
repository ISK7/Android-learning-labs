package com.example.lab7.ui.stack;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab7.R;
import com.example.lab7.databinding.FragmentSlideshowBinding;
import com.example.lab7.databinding.FragmentStackBinding;

public class StackFragment extends Fragment {

    private FragmentStackBinding binding;

    public StackFragment() {
    }

    public static StackFragment newInstance(String param1, String param2) {
        StackFragment fragment = new StackFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}