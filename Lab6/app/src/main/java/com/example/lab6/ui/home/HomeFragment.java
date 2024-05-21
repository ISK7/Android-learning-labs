package com.example.lab6.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ContextMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6.R;
import com.example.lab6.Square;
import com.example.lab6.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    Square firstSquare;
    Square secondSquare;
    Square thirdSquare;

    int squareNum = 0;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firstSquare = root.findViewById(R.id.firstSquare);
        secondSquare = root.findViewById(R.id.secondSquare);
        thirdSquare = root.findViewById(R.id.thirdSquare);

        firstSquare.setOnClickListener(v-> {
            squareNum = 1;
            changeColorInt(firstSquare, 2);
        });
        secondSquare.setOnClickListener(v-> {
            squareNum = 2;
            changeColorInt(secondSquare, 3);
        });
        thirdSquare.setOnClickListener(v-> {
            squareNum = 3;
            changeColorInt(thirdSquare, 1);

        });

        return root;
    }

    private void changeColorInt(Square sqr, int i) {
        if(i == 1) {
            sqr.setBackgroundColor(Color.RED);
            sqr.setSquareColor(Color.RED);
            sqr.setSquareText("Red");
        }
        if(i == 2) {
            sqr.setBackgroundColor(Color.BLUE);
            sqr.setSquareColor(Color.BLUE);
            sqr.setSquareText("Blue");
        }
        if(i == 3) {
            sqr.setBackgroundColor(Color.GREEN);
            sqr.setSquareColor(Color.GREEN);
            sqr.setSquareText("Green");
        }
    }

    private void changeColor(Square sqr, MenuItem item) {
        if(item.getItemId() == R.id.action_red) {
            sqr.setBackgroundColor(Color.RED);
            sqr.setSquareColor(Color.RED);
            sqr.setSquareText("Red");
        }
        if(item.getItemId() == R.id.action_blue) {
            sqr.setBackgroundColor(Color.BLUE);
            sqr.setSquareColor(Color.BLUE);
            sqr.setSquareText("Blue");
        }
        if(item.getItemId() == R.id.action_green) {
            sqr.setBackgroundColor(Color.GREEN);
            sqr.setSquareColor(Color.GREEN);
            sqr.setSquareText("Green");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (squareNum) {
            case 1:
                changeColor(firstSquare, item);
                break;
            case 2:
                changeColor(secondSquare, item);
                break;
            case 3:
                changeColor(thirdSquare, item);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}