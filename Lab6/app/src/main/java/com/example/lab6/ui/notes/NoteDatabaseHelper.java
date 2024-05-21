package com.example.lab6.ui.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static int DATABASE_VERSION = 4;

    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOTE = "note";

    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        DATABASE_VERSION = newVersion;
        onCreate(db);
    }

    public int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public boolean addNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE, note);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public List<String> getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<String> notesList = new ArrayList<>();
        if (cur != null && cur.moveToFirst()) {
            do {
                String noteText = cur.getString(cur.getColumnIndexOrThrow("note"));
                notesList.add(noteText);
            } while (cur.moveToNext());
            cur.close();
        }
        return notesList;
    }

    public boolean deleteNote(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_NOTE + "= '" + text + "'", null) > 0;
    }
}
