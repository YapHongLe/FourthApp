package com.example.fourthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pushup.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NUMBER = "number";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PUSHUP = "pushupnumber";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_NUMBER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PUSHUP + " TEXT)";
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUMBER);
        onCreate(db);
    }



    public ArrayList<Number> getAllNumber() {
        ArrayList<Number> numbers = new ArrayList<Number>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_PUSHUP + " FROM " + TABLE_NUMBER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String pushupnumber = cursor.getString(1);
                Number number = new Number(id,pushupnumber);
                numbers.add(number);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return numbers;
    }

    public long insertNumber(String pushupnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PUSHUP, pushupnumber);
        long result = db.insert(TABLE_NUMBER, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }

        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }
}
