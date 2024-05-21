package com.example.lab3_gui.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab3_gui.R;
import com.example.lab3_gui.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    String[] chooses = {"First", "Second", "Third"};

    private boolean pressed = false;
    private Integer count = 0;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button but = root.findViewById(R.id.butt);
        but.setOnClickListener(v -> {
            changeStyle(root);
        });
        Button cBut = root.findViewById(R.id.butt_clicker);
        cBut.setOnClickListener(v -> {
            countUp(cBut);
        });

        Spinner spin = root.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Chooses, androidx.transition.R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                Log.v("selector ",item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spin.setOnItemSelectedListener(itemSelectedListener);

        SeekBar sb = root.findViewById(R.id.seekbar);
        TextView seekt = root.findViewById(R.id.seektext);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Integer val = progress;
                seekt.setText(val.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return root;
    }

    protected void changeStyle(View v) {
        Button b = v.findViewById(R.id.butt);
        TextView t = v.findViewById(R.id.butt_text);
        int id;
        if (pressed) {
            id = ContextCompat.getColor(getContext(),R.color.teal_700);
            b.setBackgroundColor(id);
            id = this.getResources()
                    .getIdentifier("butt_down", "string", this.getActivity().getPackageName());
            t.setText(id);
            pressed = false;
        }
        else {
            id = ContextCompat.getColor(getContext(),R.color.teal_200);
            b.setBackgroundColor(id);
            id = this.getResources()
                    .getIdentifier("butt_press", "string", this.getActivity().getPackageName());
            t.setText(id);
            pressed = true;
        }
    }

    private void countUp (Button b) {
        count++;
        b.setText(count.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}