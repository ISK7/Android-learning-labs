package com.example.lab9.ui.slideshow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab9.R;
import com.example.lab9.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView particleImage = root.findViewById(R.id.particle_image);
        particleImage.setImageResource(R.drawable.particle_foreground);
        particleImage.setVisibility(View.INVISIBLE);

        root.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();
                createParticles(x, y, particleImage);
            }
            return true;
        });
        return root;
    }

    private void createParticles(float x, float y, ImageView particleImage) {
        particleImage.setX(x - particleImage.getWidth()/2);
        particleImage.setY(y - particleImage.getHeight()/2);

        particleImage.setVisibility(View.VISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(particleImage, "alpha", 0, 1),
                ObjectAnimator.ofFloat(particleImage, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(particleImage, "scaleY", 0, 1));
        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(
                        ObjectAnimator.ofFloat(particleImage, "alpha", 1, 0),
                        ObjectAnimator.ofFloat(particleImage, "scaleX", 1, 0),
                        ObjectAnimator.ofFloat(particleImage, "scaleY", 1, 0));
                animatorSet2.setDuration(500);
                animatorSet2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        particleImage.setVisibility(View.INVISIBLE);
                    }
                });
                animatorSet2.start();
            }
        });
        animatorSet.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}