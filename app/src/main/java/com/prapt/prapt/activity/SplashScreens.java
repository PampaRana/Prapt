package com.prapt.prapt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.prapt.prapt.R;

public class SplashScreens extends AppCompatActivity {
    private Thread timerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        timerThread = new Thread() {
            private long _splashTime = 3000;
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(_splashTime);
                    }
                } catch (InterruptedException event) {
                    event.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent  intent = new Intent(getApplicationContext(), SecondScreenActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        timerThread.start();
    }
}