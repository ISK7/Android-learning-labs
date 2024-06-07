package com.example.lab8_back;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListComponent extends LinearLayout {
    ArrayList<String> list = new ArrayList();
    Button add, delete;
    EditText label;
    TextView listView;

    Set<String> dictionary;
    String API = "dict.1.1.20240523T125915Z.fb92c46c7d7b0efd.27d8db9e5a43356f5aaf65775264c5674113dd3c";

    public ListComponent(Context context,AttributeSet attr) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.list_component, this, true);

        dictionary = new HashSet<>();
        dictionary.add("UNN");
        dictionary.add("FISH");
        dictionary.add("LIST");
        add = findViewById(R.id.add_button);
        label = findViewById(R.id.edit_text);
        delete = findViewById(R.id.delete_button);
        listView = findViewById(R.id.list_view);

        add.setOnClickListener(v -> checkString());
        delete.setOnClickListener(v -> deleteString());
    }

    private void checkString() {
        String newStr;
        newStr = label.getText().toString();
        checkSpell(newStr);
    }

    private void addString(String str) {
        label.setText("");
        if(!str.isEmpty()) list.add(str);
        showList();
    }

    private void checkSpell(String newStr) {
//        YandexSpellingChecker spellingChecker = new YandexSpellingChecker(API);
//        Log.v("Debug","before checkSpelling");
//        spellingChecker.checkSpelling(newStr, new YandexSpellingChecker.SpellingCheckCallback() {
//            @Override
//            public void onSpellingCheckComplete(boolean isCorrect) {
//                if (isCorrect) {
//                    Log.v("Debug","before addStr");
//                    addString(newStr);
//                    Log.v("Debug","after addStr");
//                } else {
//                    Log.v("Debug","before error");
//                    showDialog("Mistake","Word spelled incorrectly!","OK");
//                    Log.v("Debug","after error");
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                Log.v("Debug",errorMessage);
//                showDialog("Error", errorMessage, "OK");
//            }
//        });
//        Log.v("Debug","after checkSpelling");
        String upperCase = newStr.toUpperCase();
        if(dictionary.contains(upperCase)) {
            addString(newStr);
        }
        else {
            showDialog("Mistake","Word spelled incorrectly!","OK");
        }
    }

    private void showDialog(String title, String message, String button) {
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

    private void deleteString() {
        if(list.isEmpty()) return;
        list.remove(list.size()-1);
        showList();
    }

    private void showList() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res = res.concat(list.get(i));
            if (i < list.size() - 1) res = res.concat(", ");
        }
        if(!res.isEmpty()) res = res.substring(0, 1).toUpperCase() + res.substring(1);
        listView.setText(res);
    }
}
