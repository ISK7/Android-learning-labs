package com.example.lab9.ui.media;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.lab9.R;
import com.example.lab9.databinding.FragmentMediaBinding;

import java.io.IOException;

public class MediaFragment extends Fragment {

    private MediaViewModel mViewModel;

    FragmentMediaBinding binding;

    private PlaylistComponent playlistComponent;
    private VideoView video;

    public static MediaFragment newInstance() {
        return new MediaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MediaViewModel mediaViewModel = new ViewModelProvider(this).get(MediaViewModel.class);

        binding = FragmentMediaBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        playlistComponent = root.findViewById(R.id.playlistView);
        playlistComponent.setParent(this);
        video = root.findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.birdgif);
        video.setVideoURI(uri);
        video.start();

        return root;
    }

    void showDialog(String title, String message, String button) {
        AlertDialog.Builder al = new AlertDialog.Builder(getContext());
        al.setTitle(title)
                .setMessage(message)
                .setPositiveButton(button,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        al.create().show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MediaViewModel.class);
        // TODO: Use the ViewModel
    }

}