package com.example.lab6.ui.notes;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6.R;
import com.example.lab6.databinding.FragmentNotesBinding;
import com.example.lab6.databinding.FragmentSlideshowBinding;
import com.example.lab6.ui.slideshow.SlideshowViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private EditText note;
    private Button addB;
    private ListView notes;
    private FragmentNotesBinding binding;
    NoteDatabaseHelper helper;
    private ArrayAdapter<String> adapter;
    List<String> nots;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        note = root.findViewById(R.id.new_note);
        addB = root.findViewById(R.id.add_button);
        notes = root.findViewById(R.id.note_layout);

        helper = new NoteDatabaseHelper(this.getContext());

        helper.onUpgrade(helper.getWritableDatabase(), helper.getDatabaseVersion(), helper.getDatabaseVersion());
        addB.setOnClickListener(v -> {
            onAddButtonClick();
        });
        nots = helper.getAllNotes();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nots);
        notes.setAdapter(adapter);
        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noteToRemove = nots.get(position);
                nots.remove(position);
                adapter.notifyDataSetChanged();
                delNoteFromDB(noteToRemove);
            }
        });

        return root;
    }

    private void onAddButtonClick() {
        String newNoteText = note.getText().toString();
        nots.add(newNoteText);
        addNoteToDB(newNoteText);
        note.setText("");
        adapter.notifyDataSetChanged();
    }

    private void addNoteToDB(String text) {
        helper.addNote(text);
    }

    private void delNoteFromDB(String text) {
       helper.deleteNote(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        helper.close();
        binding = null;
    }
}
