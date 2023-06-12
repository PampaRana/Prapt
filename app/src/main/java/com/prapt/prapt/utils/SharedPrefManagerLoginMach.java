package com.prapt.prapt.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.prapt.prapt.activity.LoginScreenActivity;
import com.prapt.prapt.pogo.LoginModelMatch;

public class SharedPrefManagerLoginMach {
    private static final String SHARED_PREF_NAME = "PRAPT";
    private static final String KEY_TOKENMATCH = "keytoken";
    private static SharedPrefManagerLoginMach mInstance;
    private static Context mCtx;
    private SharedPrefManagerLoginMach(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManagerLoginMach getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerLoginMach(context);
        }
        return mInstance;
    }
    public void TokenID(LoginModelMatch user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKENMATCH, user.getTokenMach());
        editor.apply();
    }

    public LoginModelMatch getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new LoginModelMatch(
                sharedPreferences.getString(KEY_TOKENMATCH, null)
        );
    }
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mCtx, LoginScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(intent);
    }
}
