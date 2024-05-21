package com.example.lab5_6to11.ui.menu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab5_6to11.R;

import java.util.ArrayList;

public class menuFragment extends Fragment {

    private MenuViewModel mViewModel;
    int pos;

    public static menuFragment newInstance() {
        return new menuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView lv = view.findViewById(R.id.list_view);
        registerForContextMenu(lv);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("First");
        itemList.add("Second");
        itemList.add("Third");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                view.showContextMenu();
            }
        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String ans = "";
        switch (pos) {
            case 0 :
                ans += "First ";
                break;
            case 1 :
                ans += "Second ";
                break;
            case 2 :
                ans += "Third ";
                break;
        }
        if (item.getItemId() == R.id.action_a) {
            Log.d(ans, "a");
            return true;
        }
        if (item.getItemId() == R.id.action_b) {
            Log.d(ans, "b");
            return true;
        }
        if (item.getItemId() == R.id.action_c) {
            Log.d(ans, "c");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        // TODO: Use the ViewModel
    }

}