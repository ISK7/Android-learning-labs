package com.example.lab6.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.net.URL;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.util.List;

import com.example.lab6.R;
import com.example.lab6.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    LinearLayout ll;
    String url = "http://www.cbr.ru/scripts/XML_daily.asp";
    private FragmentSlideshowBinding binding;
    private String data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ll = root.findViewById(R.id.layout);

        Parser p = new Parser();
        getData(url);

        while(data == null) {}

        List<String> Names = p.parseNameValues(data);
        List<String> Rates = p.parseRateValues(data);

        if(data.equals("error")) {
            TextView errorView = new TextView(this.getContext());
            errorView.setText("Error!");

            ll.addView(errorView);
        }
        for(int i = 0; i < Names.size(); i++) {
            TextView nameView = new TextView(this.getContext());
            nameView.setText(Names.get(i));

            TextView rateView = new TextView(this.getContext());
            rateView.setText(Rates.get(i));

            ll.addView(nameView);
            ll.addView(rateView);
        }

        return root;
    }

    private void getData(String urlLink) {
        NetworkWorker worker = new NetworkWorker(this, urlLink);
        worker.start();
    }

    public void setData(String d) {
        data = d;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}