package com.example.lab5_8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Task> tasks;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        but = findViewById(R.id.add_button);
        tasks = new ArrayList<>();
        tasks.add(new Task("",""));
        SlideshowPagerAdapter adapter = new SlideshowPagerAdapter(getSupportFragmentManager());
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.add(new Task("",""));
                viewPager.getAdapter().notifyDataSetChanged();
            }
        });

        viewPager.setAdapter(adapter);
    }

    private class SlideshowPagerAdapter extends FragmentPagerAdapter {
        public SlideshowPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public Fragment getItem(int position) {
            Task task = tasks.get(position);
            TaskFragment fr = new TaskFragment();
            fr.setTask(task);
            return fr;
        }

        @Override
        public int getCount() {
            return tasks.size();
        }
    }
}