package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "yoga-class.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "class";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "Class_type";
    private static final String COLUMN_INSTRUCTOR = "Class_instructor";
    private static final String COLUMN_TIME = "Class_time";
    private static final String COLUMN_DOW = "Class_dow";
    private static final String COLUMN_CAPACITY = "Class_capacity";
    private static final String COLUMN_DUR = "Class_dur";
    private static final String COLUMN_PRICE = "Class_price";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_INSTRUCTOR + " TEXT, " +
                        COLUMN_DOW + " TEXT, " +
                        COLUMN_TIME + " INTEGER, " +
                        COLUMN_CAPACITY + " INTEGER, " +
                        COLUMN_DUR + " INTEGER, " +
                        COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String title, String instructor, String dow, Integer time, Integer capacity, Integer dur, Integer price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_INSTRUCTOR, instructor);
        cv.put(COLUMN_DOW, dow);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_CAPACITY, capacity);
        cv.put(COLUMN_DUR, dur);
        cv.put(COLUMN_PRICE, price);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
           cursor = db.rawQuery(query, null);
        }
        return cursor;

    }
    void updateData(String row_id, String title, String instructor, String dow, String time, String capacity, String dur, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_INSTRUCTOR, instructor);
        cv.put(COLUMN_DOW, dow);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_CAPACITY, capacity);
        cv.put(COLUMN_DUR, dur);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id= ?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }


    }
    void deleteOneRow (String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}