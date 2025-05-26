package com.example.tebakwarnareno;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CongratulationsActivity extends AppCompatActivity {

    private TextView textCongratulations, textSub;
    private ProgressBar progressBar;
    private Button buttonHome;

    private MediaPlayer congratsSoundPlayer; // 🔊 Sound Congratulations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        // Hubungkan komponen dari XML
        textCongratulations = findViewById(R.id.textCongratulations);
        textSub = findViewById(R.id.textSub);
        progressBar = findViewById(R.id.progressBar);
        buttonHome = findViewById(R.id.buttonHome);

        // Progress bar full
        progressBar.setProgress(100);

        // 🔊 Load & Play sound Congratulations
        congratsSoundPlayer = MediaPlayer.create(this, R.raw.congratulations);
        congratsSoundPlayer.start();

        // Tombol kembali ke stage
        buttonHome.setOnClickListener(v -> {
            // Stop sound sebelum pindah halaman
            if (congratsSoundPlayer != null && congratsSoundPlayer.isPlaying()) {
                congratsSoundPlayer.stop();
                congratsSoundPlayer.release();
                congratsSoundPlayer = null;
            }

            Intent intent = new Intent(CongratulationsActivity.this, ChoosingStageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (congratsSoundPlayer != null) {
            congratsSoundPlayer.release();
            congratsSoundPlayer = null;
        }
    }
}
