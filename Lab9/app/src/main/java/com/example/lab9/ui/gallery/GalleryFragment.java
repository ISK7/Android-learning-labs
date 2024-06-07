package com.example.lab9.ui.gallery;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab9.R;
import com.example.lab9.databinding.FragmentGalleryBinding;
import com.example.lab9.ui.media.MediaFragment;

import java.io.IOException;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    MediaPlayer OST;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView spriteAnimation = root.findViewById(R.id.sprite_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) spriteAnimation.getDrawable();
        animationDrawable.start();
        OST = new MediaPlayer(getContext());
        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.ovatsii1);
        try {
            OST.setDataSource(getContext(), uri);
            OST.prepare();
            OST.setLooping(true);
            OST.start();
        } catch (IOException ex) {
            Log.v("Ex",ex.getMessage());
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        OST.stop();
        OST.reset();
    }
}