package com.example.lab6.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6.R;
import com.example.lab6.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private Button addButton;
    private LinearLayout linearLayout;
    private int count = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addButton = root.findViewById(R.id.addButton);
        linearLayout = root.findViewById(R.id.square_layout);

        addButton.setOnClickListener(v -> {
            onAddButtonClick();
        });

        return root;
    }

    private void onAddButtonClick() {
        Button newB = new Button(this.getContext());
        newB.setText(getResources().getString(R.string.element) + count);
        count++;

        newB.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        newB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(v);
            }
        });

        linearLayout.addView(newB);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}