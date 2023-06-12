package com.prapt.prapt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prapt.prapt.R;
import com.prapt.prapt.activity.MainActivity;

public class HelpCenterActivity extends AppCompatActivity {
//    AppCompatImageView homeHeaderNavBtn;
    ImageView Img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_center_activity);
        Img = (ImageView)findViewById(R.id.Img);
//        homeHeaderNavBtn = (AppCompatImageView)findViewById(R.id.homeHeaderNavBtn);
//        homeHeaderNavBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//            }
//        });
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
