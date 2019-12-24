package com.example.nottaker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nottaker.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final TextView txt = findViewById(R.id.titleTxt);
        final ImageView img = findViewById(R.id.logoImg);

        Animation fadeIn = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);
        final Animation fadeOut = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadeout);

        fadeIn.reset();
        img.clearAnimation();
        txt.clearAnimation();
        img.startAnimation(fadeIn);
        txt.startAnimation(fadeIn);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fadeOut.reset();
                img.clearAnimation();
                txt.clearAnimation();
                img.startAnimation(fadeOut);
                txt.startAnimation(fadeOut);
            }
        }, 6000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },8500);
    }
}
