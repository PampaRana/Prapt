package com.prapt.prapt.utils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.prapt.prapt.activity.LoginScreenActivity;
import com.prapt.prapt.pogo.LoginModel;

public class SharedPrefManagerLogin {
    private static final String SHARED_PREF_NAME = "PRAPT";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_USERID = "keyUSERID";
    private static final String KEY_NAME = "keyNAME";
    private static final String KEY_CODE = "keyCODE";
    private static SharedPrefManagerLogin mInstance;
    private static Context mCtx;
    private SharedPrefManagerLogin(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManagerLogin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerLogin(context);
        }
        return mInstance;
    }
    public void TokenID(LoginModel user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, user.getToken());
        editor.putString(KEY_USERID, user.getUserId());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_CODE, user.getCode());
        editor.apply();
    }
    public LoginModel getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new LoginModel(
                sharedPreferences.getString(KEY_TOKEN, null),
                sharedPreferences.getString(KEY_USERID, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_CODE, null)
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
