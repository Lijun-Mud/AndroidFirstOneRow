package com.example.hb49417.myapplication.tio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by LIJUN on 7/23/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    public static final String CREATE_BOOK="CREATE TABLE `Books` ( " +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " `name` TEXT NOT NULL, " +
            " `author` TEXT, " +
            " `price` REAL NOT NULL " +
            ")";
    public static final String CREATE_CATEGORY="CREATE TABLE `category` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " `name` TEXT NOT NULL " +
            ")";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context, "created db!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(context, "upgraded db!", Toast.LENGTH_SHORT).show();
    }
}
