package com.example.lab7.ui.home;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab7.R;
import com.example.lab7.databinding.FragmentHomeBinding;
import com.example.lab7.ui.CustomViews.CircleView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CircleView upper, middle, bottom;
    ImageView human;
    private boolean go;
    Handler handler = new Handler();
    ObjectAnimator animator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        upper = root.findViewById(R.id.upper_lamp);
        middle = root.findViewById(R.id.middle_lamp);
        bottom = root.findViewById(R.id.bottom_lamp);
        human = root.findViewById(R.id.image);

        runCicle();

        Animator anim = AnimatorInflater.loadAnimator(root.getContext(),R.animator.left_to_right);
        AnimatorSet aSet = (AnimatorSet)anim;
        for(Animator a : aSet.getChildAnimations()) {
             animator = (ObjectAnimator)a;
        }
        animator.setTarget(human);
        go = true;

        return root;
    }

    Thread cycle = new Thread(new Runnable(){
        @Override
        public void run() {
            go = true;
            while(go) {
                getActivity().runOnUiThread(turnRed);
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                    Log.v("Traffic Light exception!", ex.toString());
                    break;
                }
                getActivity().runOnUiThread(turnYellow);
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                    Log.v("Traffic Light exception!", ex.toString());
                    break;
                }
                getActivity().runOnUiThread(turnGreen);
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                    Log.v("Traffic Light exception!", ex.toString());
                    break;
                }
                getActivity().runOnUiThread(turnYellow);
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                    Log.v("Traffic Light exception!", ex.toString());
                    break;
                }
            }
        }
    });
    Runnable turnRed = new Runnable() {
        @Override
        public void run() {
            TurnOn(upper,Color.RED);
            TurnOut(middle);
        }
    };
    Runnable turnYellow = new Runnable() {
        @Override
        public void run() {
            TurnOn(middle,Color.YELLOW);
            TurnOut(bottom);
        }
    };
    Runnable turnGreen = new Runnable() {
        @Override
        public void run() {
            TurnOn(bottom,Color.GREEN);
            TurnOut(middle);
            TurnOut(upper);
            animator.start();
        }
    };

    public static float convertDpToPixel(float dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private void TurnOn(CircleView circle, int color) {
        circle.setCircleColor(color);
    }
    private void TurnOut(CircleView circle) {
        circle.setCircleColor(Color.BLACK);
    }

    private void runCicle() {
        cycle.start();
    }

    @Override
    public void onDestroyView() {
        go = false;
        super.onDestroyView();
        binding = null;
    }
}