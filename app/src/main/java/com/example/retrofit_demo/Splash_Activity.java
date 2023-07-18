package com.example.retrofit_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity
{
    ImageView imageView;
    Runnable runnable;
    public static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splashimg);

        preferences = getSharedPreferences("myperf",MODE_PRIVATE);
        editor=preferences.edit();
        int login = preferences.getInt("login",0);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
        imageView.setAnimation(animation);
        runnable = new Runnable() {
            @Override
            public void run() {
                if(login==0) {
                    Intent intent = new Intent(Splash_Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,1000);
    }
}