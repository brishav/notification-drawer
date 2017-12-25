package com.example.notificationdrawerdemo.other;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import com.example.notificationdrawerdemo.activity.MainActivity;
import com.example.notificationdrawerdemo.activity.SignUpActivity;

/**
 * Created by Brishav on 12/18/2017.
 */

public class SessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;
    int PRIVATE_MODE = 0;

    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String PREF_NAME = "brishav";
    private static final String PREF_IMG = "https://lh3.googleusercontent.com/-keyAPT2Z8jc/AAAAAAAAAAI/AAAAAAAAAXk/LXXAiPp8wck/s640/photo.jpg";

    //private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_IMG = "image";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveUsername(String name) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, name);

        editor.commit();
    }

    public void saveImgUrl(String img) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_IMG, img);

        editor.commit();
    }

   /* public void checkLogin() {
        if (!this.isLoggedIn()) {

            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }

    }
*/
    public String getUserDetails() {

        return (pref.getString(KEY_NAME, null));

    }

    public String getUserImage() {

        return (pref.getString(KEY_IMG, null));

    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();


        Intent i = new Intent(_context, SignUpActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);


    }
}
