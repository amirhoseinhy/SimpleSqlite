package com.niksan.simplesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MysqliteDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "person.db";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String USER_ID = "ID";
    public static final String USER_NAME = "NAME";


    public MysqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                USER_NAME + " TEXT NOT NULL ) ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
