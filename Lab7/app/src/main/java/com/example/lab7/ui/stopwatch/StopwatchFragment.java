package com.example.lab7.ui.stopwatch;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab7.R;
import com.example.lab7.databinding.FragmentSlideshowBinding;
import com.example.lab7.databinding.FragmentStopwatchBinding;
import com.example.lab7.ui.slideshow.SlideshowViewModel;

public class StopwatchFragment extends Fragment {

    private FragmentStopwatchBinding binding;
    private TimerComponent timerComponent;
    private Button startStopButton;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int seconds = 0;
    private boolean isRunning = false;

    public static StopwatchFragment newInstance() {
        return new StopwatchFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding =  FragmentStopwatchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        timerComponent = root.findViewById(R.id.timerComponent);
        startStopButton = root.findViewById(R.id.startStopButton);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        return root;
    }

    private void startTimer() {
        isRunning = true;
        startStopButton.setText(R.string.stop);
        runnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int remainingSeconds = seconds % 60;
                timerComponent.setTime(hours, minutes, remainingSeconds);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void stopTimer() {
        isRunning = false;
        startStopButton.setText(R.string.start);
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}