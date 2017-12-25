package com.example.notificationdrawerdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brishav on 12/18/2017.
 */

public class DbHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "Users";

    public static final String COLUMN_ID = "userId";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PASSWORD = "userPassword";
    public static final String COLUMN_IMAGEURL = "userImageUrl";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_USERS + " ("+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_USERNAME + " TEXT, "+
            COLUMN_PASSWORD + " TEXT, "+
            COLUMN_IMAGEURL + " TEXT " +
            ") ";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




        @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL(TABLE_CREATE);

    }
}
