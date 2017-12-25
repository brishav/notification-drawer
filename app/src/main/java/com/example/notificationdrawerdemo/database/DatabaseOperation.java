package com.example.notificationdrawerdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.notificationdrawerdemo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brishav on 12/18/2017.
 */

public class DatabaseOperation {

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allcolumns = {
            DbHandler.COLUMN_ID,
            DbHandler.COLUMN_USERNAME,
            DbHandler.COLUMN_PASSWORD,
            DbHandler.COLUMN_IMAGEURL,
            };

    public DatabaseOperation(Context context) {
        dbhandler = new DbHandler(context);
    }

    public void open() {
        Log.d("database123","database opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close() {
        Log.d("database123","database closed");
        dbhandler.close();
    }

    public User addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DbHandler.COLUMN_USERNAME, user.getUsername());
        values.put(DbHandler.COLUMN_PASSWORD, user.getUserpassword());
        values.put(DbHandler.COLUMN_IMAGEURL, user.getUserImageUrl());
        int insertid = (int) database.insert(DbHandler.TABLE_USERS,null,values);
        user.setUserId(insertid);
        Log.e("Values", "added");
        return user;
    }

    public List<User> getAllUser() {

        Cursor cursor = database.query(DbHandler.TABLE_USERS, allcolumns, null, null, null, null, null);

        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                User user1 = new User();
                user1.setUserId(cursor.getInt(cursor.getColumnIndex(DbHandler.COLUMN_ID)));
                user1.setUsername(cursor.getString(cursor.getColumnIndex(DbHandler.COLUMN_USERNAME)));
                user1.setUserpassword(cursor.getString(cursor.getColumnIndex(DbHandler.COLUMN_PASSWORD)));
                user1.setUserImageUrl(cursor.getString(cursor.getColumnIndex(DbHandler.COLUMN_IMAGEURL)));
                users.add(user1);

                Log.d("Data retrived", user1.getUsername());
            }
        }
        return users;
    }
}
