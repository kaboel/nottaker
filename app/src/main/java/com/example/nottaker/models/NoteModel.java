package com.example.nottaker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nottaker.helpers.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteModel extends SQLiteOpenHelper {

    // database info
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "noteDatabase";
    private static final String DB_TABLE = "noteTable";

    // table colums
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public NoteModel(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase con) {
        String query = "CREATE TABLE "+DB_TABLE+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";

        con.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase con, int oldVer, int newVer) {
        if (oldVer >= newVer) {
            return;
        }

        con.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
    }

    public long addNote (Note note) {
        SQLiteDatabase con = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put(KEY_TITLE, note.getTitle());
        data.put(KEY_CONTENT, note.getContent());
        data.put(KEY_DATE, note.getDate());
        data.put(KEY_TIME, note.getTime());

        long id = con.insert(DB_TABLE, null, data);
        Log.d("data id: ", "id --> "+id);
        Log.d("data id: ", "title --> "+note.getTitle());
        Log.d("data id: ", "content --> "+note.getContent());
        Log.d("data id: ", "date --> "+note.getDate());
        Log.d("data id: ", "time --> "+note.getTime());

        return id;
    }

    public Note getNote(long id) {
        SQLiteDatabase con = this.getReadableDatabase();
        Cursor cursor = con.query(
            DB_TABLE,
            new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME},
            KEY_ID+"=?",
            new String[]{String.valueOf(id)},
            null,
            null,
            null
        );

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return new Note(
            cursor.getLong(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4)
        );

    }

    public List<Note> getNotes() {
        SQLiteDatabase con = this.getReadableDatabase();
        List<Note> notes = new ArrayList<>();

        String query = "SELECT * FROM "+ DB_TABLE +" ORDER BY "+ KEY_ID +" DESC";
        Cursor cursor = con.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                Log.d("Cursor-ID", "ID -->> "+ cursor.getString(3));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        return notes;
    }

    public int update(long id, Note note) {
        SQLiteDatabase con = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(KEY_TITLE, note.getTitle());
        data.put(KEY_CONTENT, note.getContent());
        data.put(KEY_DATE, note.getDate());
        data.put(KEY_TIME, note.getTime());
        return con.update(DB_TABLE, data,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }

    public int destroy(long id) {
        SQLiteDatabase con = this.getWritableDatabase();
        return con.delete(DB_TABLE, KEY_ID+"=?", new String[]{String.valueOf(id)});
    }

}
