package com.example.kisgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class DefeatComponent extends ConstraintLayout {

    TextView scoreView;
    GameScreen parent;
    Button tryAgain;

    public DefeatComponent(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.defeat_component, this, true);

        parent = (GameScreen) context;
        scoreView = findViewById(R.id.score_text);
        tryAgain = findViewById(R.id.try_again_button);
        tryAgain.setOnClickListener(v -> onTryAgainButtonClick());
    }

    public void setScore(int score) {
        String res = getResources().getString(R.string.score);
        res = res.concat(String.format(Locale.ENGLISH,"%d",score));
        scoreView.setText(res);
    }
    private void onTryAgainButtonClick() {
        parent.again();
    }
}
