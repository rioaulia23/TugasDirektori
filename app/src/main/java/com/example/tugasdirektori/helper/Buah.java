package com.example.tugasdirektori.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Buah extends SQLiteOpenHelper {
    final static String NAMADB = "buahs.db";
    final static int VERSIDB = 1;

    public Buah(Context context) {
        super(context, NAMADB, null, VERSIDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreate = "CREATE TABLE " +
                "buah(_id integer primary key autoincrement," +
                "name text, type text);";
        db.execSQL(queryCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropQuery = "DROP TABLE IF EXISTS buah";
        db.execSQL(dropQuery);
        onCreate(db);
    }
}
