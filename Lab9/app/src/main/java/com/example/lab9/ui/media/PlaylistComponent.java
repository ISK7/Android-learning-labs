package com.example.lab9.ui.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lab9.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlaylistComponent extends ConstraintLayout {

    private Button previous, pause, next;
    MediaPlayer player;

    ArrayList<Uri> files;
    int index;
    boolean isPause;
    MediaFragment parent;

    public PlaylistComponent(Context context) {
        super(context);
        init(context, null);
    }

    public PlaylistComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.component_playlist, this, true);

        previous = findViewById(R.id.button_previous);
        pause = findViewById(R.id.button_pause);
        next = findViewById(R.id.button_next);
        player = new MediaPlayer();
        files = new ArrayList<>();
        files.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ovatsii1));
        files.add(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ovatsii2));
        index = 0;
        isPause = true;

        previous.setOnClickListener(v -> onPreviousClick());
        pause.setOnClickListener(v -> onPauseClick());
        next.setOnClickListener(v -> onNextClick());
    }

    public void setParent(MediaFragment mediaFragment) {
        parent = mediaFragment;
    }

    private void increase(){
        index++;
        if(index >= files.size()) index = 0;
    }

    private void decrease(){
        index--;
        if(index < 0) index = files.size()-1;
    }

    private void onPreviousClick(){
        if(player.isPlaying()) player.stop();
        player.reset();
        decrease();
        try {
            player.setDataSource(getContext(),files.get(index));
            player.prepare();
        } catch (IOException ex) {
            Log.v("Ex",ex.getMessage());
            parent.showDialog("Error", ex.getMessage(), "OK");
        }
        if(!isPause) player.start();
    }
    private void onPauseClick() {
        if(isPause) {
            pause.setText("Pause");
            player.start();
        }
        else {
            pause.setText("Start");
            player.pause();
        }
        isPause = !isPause;
    }
    private void onNextClick(){
        if(player.isPlaying()) player.stop();
        player.reset();
        increase();
        try {
            player.setDataSource(getContext(),files.get(index));
            player.prepare();
        } catch (IOException ex) {
            Log.v("Ex",ex.getMessage());
            parent.showDialog("Error", ex.getMessage(), "OK");
        }
        if(!isPause) player.start();
    }
}
