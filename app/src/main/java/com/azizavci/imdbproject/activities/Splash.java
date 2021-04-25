package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.azizavci.imdbproject.R;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topAnimation;
    Animation bottomAnimation;
    ImageView logo;
    TextView designedBy, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        getSplash();

    }

    private void getSplash() {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        logo = findViewById(R.id.logo);
        designedBy = findViewById(R.id.designedBy);
        year = findViewById(R.id.year);

        logo.setAnimation(topAnimation);
        designedBy.setAnimation(bottomAnimation);
        year.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //başlangıç sayfasından anasayfaya
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }


}