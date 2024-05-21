package com.example.lab3_rectangles.ui.animated;

import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.lab3_rectangles.R;
import com.example.lab3_rectangles.Square;
import com.example.lab3_rectangles.databinding.FragmentSevenBinding;

public class SevenFragment extends Fragment {

    private SevenViewModel mViewModel;

    private View sevenView;
    private FragmentSevenBinding binding;
    public static SevenFragment newInstance() {
        return new SevenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSevenBinding.inflate(inflater, container, false);
        sevenView = binding.getRoot();

        Button button = sevenView.findViewById(R.id.animate_button);

        button.setOnClickListener(v -> {
            animateView(R.id.layout);
        });
        return sevenView;
    }

    protected void animateView(int ID){
        GridLayout square = sevenView.findViewById(ID);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.down_n_scale);
        square.startAnimation(animation);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}