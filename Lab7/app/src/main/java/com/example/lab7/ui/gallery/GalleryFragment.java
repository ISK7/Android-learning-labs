package com.example.lab7.ui.gallery;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab7.R;
import com.example.lab7.databinding.FragmentGalleryBinding;

import java.util.function.Function;

public class GalleryFragment extends Fragment implements View.OnTouchListener {

    private FragmentGalleryBinding binding;;
    private AnimatorSet aSet;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;

        aSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.down_rotate_paint);
        aSet.setTarget(textView);
        root.setOnTouchListener(this);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                aSet.start();
                return true;
            case MotionEvent.ACTION_UP:
                aSet.reverse();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        aSet.cancel();
        binding = null;
    }
}