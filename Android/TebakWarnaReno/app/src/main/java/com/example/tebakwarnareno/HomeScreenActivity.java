package com.example.tebakwarnareno;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenActivity extends AppCompatActivity {

    private ImageView logoImage;
    private RelativeLayout adventureButton;
    private RelativeLayout leaderboardButton;
    private RelativeLayout challengeButton; // ✅ Tombol Challenge
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        logoImage = findViewById(R.id.logoImage);
        adventureButton = findViewById(R.id.adventureButton);
        leaderboardButton = findViewById(R.id.leaderboardButton);
        challengeButton = findViewById(R.id.challengeButton); // ✅ Hubungkan tombol Challenge

        // Animasi bounce untuk logo
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce2);
        logoImage.startAnimation(bounce);

        // Animasi glow
        Animation glow = AnimationUtils.loadAnimation(this, R.anim.glow);
        adventureButton.startAnimation(glow);
        leaderboardButton.startAnimation(glow);
        challengeButton.startAnimation(glow); // ✅ Animasi challenge

        // Klik tombol Adventure
        adventureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        // Klik tombol Leaderboard
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        // ✅ Klik tombol Challenge ke halaman pemilihan stage
        challengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, ChoosingStageActivity.class);
                startActivity(intent);
            }
        });

        // Musik homescreen
        mediaPlayer = MediaPlayer.create(this, R.raw.homescreen);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
