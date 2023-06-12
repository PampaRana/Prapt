package com.prapt.prapt.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.prapt.prapt.R
import com.prapt.prapt.utils.Config
import com.prapt.prapt.utils.SharedPreferencesClass

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            if ( SharedPreferencesClass.retrieveData(applicationContext,Config.LOGIN_STATUS)
                    .equals("1")) {
                val i = Intent(
                    this@SplashScreen,
                    MainActivity::class.java
                )
                startActivity(i)
                finish()
            } else {
                val i = Intent(
                    this@SplashScreen,
                    SecondScreenActivity::class.java
                )
                startActivity(i)
                finish()
            }

        }, 2000)
    }
}