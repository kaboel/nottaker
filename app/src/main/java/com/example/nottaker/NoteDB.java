package com.example.nottaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDB extends SQLiteOpenHelper {

    // database info
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "ndb";
    private static final String DB_TABLE = "ntable";

    // table colums
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    NoteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase con) {
        String query = "CREATE TABLE " + DB_TABLE +"(";
        query += KEY_ID + " INT PRIMARY KEY, ";
        query += KEY_TITLE + " TEXT, ";
        query += KEY_CONTENT + " TEXT, ";
        query += KEY_DATE + " TEXT, ";
        query += KEY_TIME + " TEXT";
        query += ")";

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

        return id;
    }
}
