package com.example.mc.NotesAppSKL;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NoteDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE notes ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "location TEXT, " +
                "message TEXT, " +
                "voice TEXT, " +
                "picture TEXT)";

        // create books table
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS notes");

        // create fresh books table
        this.onCreate(db);
    }

    /*********
     * CRUD  *
     *********/

    // Books table name
    private static final String TABLE_NOTES = "notes";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_VOICE = "voice";
    private static final String KEY_PICTURE = "picture";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_LOCATION,KEY_MESSAGE,KEY_VOICE,KEY_PICTURE};

    public void addNote(Note note){
        Log.d("addNote", note.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_LOCATION, note.getLocation());
        values.put(KEY_MESSAGE, note.getMessage());
        values.put(KEY_VOICE, note.getVoice());
        values.put(KEY_PICTURE, note.getPicture());

        db.insert(TABLE_NOTES,
                null,
                values);

        db.close();
    }

    // Getting one Note
    public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,
                        KEY_TITLE, KEY_LOCATION, KEY_MESSAGE,KEY_VOICE,KEY_PICTURE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Note note = new Note(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return note
        return note;
    }

    // Updating a note
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_LOCATION, note.getLocation());
        values.put(KEY_MESSAGE, note.getMessage());
        values.put(KEY_VOICE, note.getVoice());
        values.put(KEY_PICTURE, note.getPicture());
        // updating row
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    // Deleting a note
    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[] { String.valueOf(note.getId()) });
        db.close();
    }

    // Get All Notes
    public List<Note> getAllNotes() {
        List<Note> notes = new LinkedList<Note>();

        String query = "SELECT  * FROM " + TABLE_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Note note = null;
        if (cursor.moveToFirst()) {
            do {
                note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setLocation(cursor.getString(2));
                note.setMessage(cursor.getString(3));
                note.setVoice(cursor.getString(4));
                note.setPicture(cursor.getString(5));

                // Add note to Notes
                notes.add(note);
            } while (cursor.moveToNext());
        }

        Log.d("getAllNotes()", notes.toString());

        // return notes
        return notes;
    }


}
