package com.example.kisgame;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    TextView dpsView, scoreView;
    FishComponent fishComponent;
    DefeatComponent gameOver;
    ConstraintLayout root;
    int columnsCount = 1;
    private int pickupHeight = 275;
    private Handler handler;
    private Runnable runnable;
    ArrayList<ConstraintLayout> columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dpsView = findViewById(R.id.dps);
        dpsView.setText(String.format(Locale.ENGLISH,"%d",Resources.getDps()));
        scoreView = findViewById(R.id.score);
        fishComponent = findViewById(R.id.fish_playable);
        dpsView.setZ(1);
        scoreView.setZ(1);
        root = findViewById(R.id.root);
        columns = new ArrayList<>();
        handler = new Handler(Looper.getMainLooper());
        runnable = this::move;

        reset();
        handler.postDelayed(runnable, 0);
    }

    private void testDeathAnimation() {
        PipeColumn pipe = new PipeColumn(this);
        root.addView(pipe);
        pipe.setX(100);
        pipe.setY(100);
        pipe.decreaseHp(11);
    }
    private void move() {
        List<ConstraintLayout> removeList = new ArrayList<>();
        boolean isPicked = false;
        for (int i = 0; i < columns.size(); i++) {
            ConstraintLayout view = columns.get(i);
            view.setX(view.getX() - Resources.getSpeed());
            if (view.getX() + view.getWidth() < 0) {
                root.removeView(view);
                removeList.add(view);
            }
            view.invalidate();
            if(checkFishCollision(view)) {
                if(view.getClass().equals(PipeColumn.class)) {
                    if(tryToDestroyPipe((PipeColumn) view)) {
                        if(view.getAlpha() - 0.05 < 0.0) {
                            root.removeView(view);
                            removeList.add(view);
                        }
                    }
                    else {
                        defeat();
                        return;
                    }
                }
                else {
                    int fm = (int) fishComponent.getY() + fishComponent.getHeight()/2;
                    PickUp pu = (PickUp) view;
                    if((pu.getY()< 1044 && fm < 1044) || (pu.getY() > 1044 && fm > 1044))
                        newDps(pu);
                    isPicked = true;
                }
            }
        }
        if(isPicked) {
            for(ConstraintLayout cl: columns) {
                removeList.add(cl);
                root.removeView(cl);
            }
        }

        fishComponent.setY(Math.max(fishComponent.getY() + fishComponent.getSpeed(),fishComponent.getHeight()));
        fishComponent.setSpeed(fishComponent.getSpeed() + Resources.getG());
        fishComponent.resetRotation();
        if(fishComponent.getY() > root.getHeight() - fishComponent.getHeight()) {
            defeat();
            return;
        }

        for(ConstraintLayout dView : removeList) {
            columns.remove(dView);
        }
        removeList.clear();
        if (columns.size() < columnsCount) {
            addColumn();
        }

        handler.postDelayed(runnable, 50);
    }

    private boolean tryToDestroyPipe(PipeColumn pipe) {
        if(!pipe.isAlive()) return true;
        pipe.decreaseHp(Resources.getDps());
        if(pipe.isAlive()) return false;
        Resources.setScore(Resources.getScore()+1);
        scoreView.setText(String.format(Locale.ENGLISH,"%d",Resources.getScore()));
        return true;
    }

    private void newDps(PickUp pu) {
        int nd = 0;
        switch (pu.getOperation()) {
            case ADDITION: {
                nd = Resources.getDps() + pu.getValue();
                break;
            }
            case MULTIPLICATION: {
                nd = Resources.getDps() * pu.getValue();
                break;
            }
            case SUBTRACTION: {
                nd = Resources.getDps() - pu.getValue();
                break;
            }
            case DIVISION: {
                nd = Resources.getDps() / pu.getValue();
                break;
            }
        }
        dpsView.setText(String.format(Locale.ENGLISH,"%d",nd));
        Resources.setDps(nd);
    }

    private boolean checkFishCollision(ConstraintLayout view) {
        int fr = (int)fishComponent.getX() + fishComponent.getWidth();
        int vl = (int)view.getX();
        return (fr > vl);
    }

    private void defeat() {
        scoreView.setVisibility(View.INVISIBLE);
        dpsView.setVisibility(View.INVISIBLE);
        gameOver = new DefeatComponent(this);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        gameOver.setLayoutParams(lp);
        gameOver.setScore(Resources.getScore());
        root.addView(gameOver);
    }

    private void addColumn() {
        List<ConstraintLayout> newColumns = generateColumn();
        for(ConstraintLayout newColumn : newColumns) {
            root.addView(newColumn);
            columns.add(newColumn);
        }
    }

    private List<ConstraintLayout> generateColumn() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        Random random = new Random();
        int num = Math.abs(random.nextInt() % 4);
        if (num % 4 == 0) {
            PipeColumn newPipe = new PipeColumn(this);
            newPipe.setScaleY((float) 1.05);
            newPipe.setX(screenWidth);
            newPipe.setHp(Resources.getMeta());
            List<ConstraintLayout> res = new ArrayList<>();
            res.add(newPipe);
            return res;
        }
        PickUp pickUp1 = generatePickup(num);
        num += 1;
        PickUp pickUp2 = generatePickup(num);
        pickUp1.setX(screenWidth);
        pickUp2.setX(screenWidth);

        int maxHeight = getResources().getDisplayMetrics().heightPixels;
        List<ConstraintLayout> res = new ArrayList<>();
        res.add(pickUp1);
        res.add(pickUp2);

        int id1 = ViewGroup.generateViewId();
        int id2 = ViewGroup.generateViewId();
        pickUp1.setId(id1);
        pickUp2.setId(id2);

        ConstraintLayout.LayoutParams lp1 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
        ConstraintLayout.LayoutParams lp2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);

        if(Resources.getDps() < 100) {
            num = Math.abs(random.nextInt()%4);
            num += 2;
            int mulDps = Resources.getDps() * num;
            int multModule = num;
            int difference = random.nextInt() % (mulDps/5 + 1);
            int addModule = mulDps + difference - Resources.getDps();
            int newMeta = (mulDps *2 + difference)/2;
            Resources.setMeta(newMeta);
            if(num % 2 == 0) {
                pickUp1.setOperation(Operation.ADDITION);
                pickUp1.setValue(addModule);
                pickUp2.setOperation(Operation.MULTIPLICATION);
                pickUp2.setValue(multModule);
            }
            else {
                pickUp1.setOperation(Operation.MULTIPLICATION);
                pickUp1.setValue(multModule);
                pickUp2.setOperation(Operation.ADDITION);
                pickUp2.setValue(addModule);
            }
        }
        else {
            num = Math.abs(random.nextInt()%4) + 2;
            int mulDps = Resources.getDps() / num;
            int divModule = num;
            int difference = random.nextInt() % (mulDps/5);
            int subModule = Resources.getDps() - mulDps + difference;
            int newMeta = (mulDps *2 + difference)/2;
            Resources.setMeta(newMeta);
            if(num % 2 == 0) {
                pickUp2.setOperation(Operation.DIVISION);
                pickUp2.setValue(divModule);
                pickUp1.setOperation(Operation.SUBTRACTION);
                pickUp1.setValue(subModule);
            }
            else {
                pickUp2.setOperation(Operation.SUBTRACTION);
                pickUp2.setValue(subModule);
                pickUp1.setOperation(Operation.DIVISION);
                pickUp1.setValue(divModule);
            }
        }
        pickUp1.setScaleY((float)4.5);
        pickUp2.setScaleY((float)4.5);
        pickUp1.setY(pickupHeight);
        pickUp2.setY((int)(pickupHeight*5.5));
        return res;
    }

    private PickUp generatePickup(int num) {
        PickUp pickUp;
        switch (num) {
            case 3: {
                pickUp = new PickUp(this);
                pickUp.setColorNum(1);
                return pickUp;
            }
            case 1: {
                pickUp = new PickUp(this);
                pickUp.setColorNum(2);
                return pickUp;
            }
            case 2: {
                pickUp = new PickUp(this);
                pickUp.setColorNum(3);
                return pickUp;
            }
            default: {
                pickUp = new PickUp(this);
                pickUp.setColorNum(4);
                return pickUp;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        fishComponent.setSpeed(Resources.getJumpSpeed());
        return true;
    }

    private void reset() {
        Resources.setDps(Resources.getStartDps());
        Resources.setScore(0);
        scoreView.setText(String.format(Locale.ENGLISH,"%d",Resources.getScore()));
        dpsView.setText(String.format(Locale.ENGLISH,"%d",Resources.getDps()));
        Resources.setMeta(Resources.getDefaultMeta());
        fishComponent.setY(1040);
        fishComponent.setSpeed(Resources.getJumpSpeed());
    }

    public void again() {
        for(ConstraintLayout column : columns)
            root.removeView(column);
        columns.clear();
        root.removeView(gameOver);
        dpsView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        reset();
        handler.postDelayed(runnable, 0);
    }
}