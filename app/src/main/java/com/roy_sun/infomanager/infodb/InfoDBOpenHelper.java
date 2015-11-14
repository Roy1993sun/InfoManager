package com.roy_sun.infomanager.infodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class InfoDBOpenHelper extends SQLiteOpenHelper {

    public InfoDBOpenHelper (Context context) {
        super(context, "information.db", null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("create table info (id integer primary key autoincrement, person_id varchar(20), name varchar(20), phone varchar(20))");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
