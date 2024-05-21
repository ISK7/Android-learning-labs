package com.example.lab6.ui.slideshow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkWorker extends Thread{
    SlideshowFragment parent;
    String urlLink;
    public NetworkWorker(SlideshowFragment p, String url) {
        parent = p;
        urlLink = url;
    }

    @Override
    public void run() {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlLink);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                parent.setData("error");
            }
            conn.disconnect();
            parent.setData(result.toString());
        }catch (Exception ex) {
            parent.setData("error");
        }
    }
}
