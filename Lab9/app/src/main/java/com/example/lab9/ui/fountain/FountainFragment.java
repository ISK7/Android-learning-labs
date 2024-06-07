package com.example.lab9.ui.fountain;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab9.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FountainFragment extends Fragment {

    private FountainViewModel mViewModel;

    private int maxParticles = 100;
    private int timeToLive = 5000;
    private int particlesPerSecond = 5;
    private int h,w;
    Random random;
    ArrayList<Image> particles;
    View root;
    Handler handler;
    Runnable runnable;

    public static FountainFragment newInstance() {
        return new FountainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_fountain, container, false);


        Drawable particleDrawable = ContextCompat.getDrawable(getContext(), R.drawable.particle_foreground);

        particles = new ArrayList<>();
        handler = new Handler(Looper.getMainLooper());
        runnable = this::move;
        handler.postDelayed(runnable, 1000);

        h = 1700;
        w = 300;
        random = new Random();

        return root;
    }

    private void move() {
        List<Image> delList = new ArrayList<>();
        createParticle();
        for(Image prt : particles) {
            prt.moveParticle();
            if(prt.getTimeToLive() <= 0) delList.add(prt);
        }
        for(Image particle : delList) {
            particles.remove(particle);
        }
        handler.postDelayed(runnable, 10);
    }

    private void createParticle() {
        ImageView pt = new ImageView(getContext());
        pt.setImageResource(R.drawable.particle_foreground);
        ((ConstraintLayout)root).addView(pt);
        pt.setScaleX((float)0.2);
        pt.setScaleY((float)0.2);
        pt.setX(w);
        pt.setY(h);
        Image p = new Image();
        p.setImageView(pt);
        p.setVertical(Resourses.getDefaultSpeed());
        int ang = random.nextInt()%10;
        p.setAngle(ang);
        p.setTimeToLive(timeToLive);
        particles.add(p);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FountainViewModel.class);
        // TODO: Use the ViewModel
    }

}