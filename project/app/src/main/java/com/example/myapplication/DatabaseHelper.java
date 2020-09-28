package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="user1.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql="create table ucid( username varchar(20)primary key autoincrement,password varchar(20),role varchar(20)" +
                " ,lastname varchar(20),firstname varchar(20),phone varchar(20),email varchar(20),address varchar(20)" +
                ",city varchar(20),state varchar(20),zipcode varchar(20),member varchar(20),status varchar(20))";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
