package com.example.tebakwarnareno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 5000; // 5 Detik
    private TextView appTitle, studioName;
    private ImageView logoImage;
    private ProgressBar progressBar;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appTitle = findViewById(R.id.appTitle);
        studioName = findViewById(R.id.studioName);
        logoImage = findViewById(R.id.logoImage);
        progressBar = findViewById(R.id.progressBar);

        // Animasi Fade In untuk teks
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(2000);
        appTitle.startAnimation(fadeIn);
        studioName.startAnimation(fadeIn);

        // Animasi Bounce untuk logo
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        logoImage.startAnimation(bounce);

        // Simulasikan progress bar berjalan
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try {
                        Thread.sleep(50); // 50ms tiap progress
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // Pindah ke HomeScreenActivity setelah 5 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, userActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
