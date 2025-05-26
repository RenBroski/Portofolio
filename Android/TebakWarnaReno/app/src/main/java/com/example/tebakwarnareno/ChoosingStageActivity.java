package com.example.tebakwarnareno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChoosingStageActivity extends AppCompatActivity {

    private GridLayout stageGrid;
    private SharedPreferences prefs;
    private int maxStageUnlocked;

    private MediaPlayer mediaPlayer; // Untuk lagu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_stage);

        // Inisialisasi MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.homescreen);
        mediaPlayer.setLooping(true); // Lagu berulang
        mediaPlayer.start(); // Mulai lagu

        stageGrid = findViewById(R.id.stageGrid);
        prefs = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        maxStageUnlocked = prefs.getInt("maxStageUnlocked", 1); // default stage 1 terbuka

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10); // total 10 stage
        progressBar.setProgress(maxStageUnlocked);

        stageGrid.setColumnCount(2); // 2 kolom

        // Update grid pertama kali
        updateGrid();

        // Tombol kembali
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    // Method untuk update stage setelah selesai main
    public void updateMaxStageUnlocked(int completedStage) {
        if (completedStage >= maxStageUnlocked && completedStage < 10) {
            maxStageUnlocked = completedStage + 1;
            prefs.edit().putInt("maxStageUnlocked", maxStageUnlocked).apply();
        }
    }

    // Auto refresh saat kembali ke activity
    @Override
    protected void onResume() {
        super.onResume();

        // Ambil data terbaru
        maxStageUnlocked = prefs.getInt("maxStageUnlocked", 1);

        // Update Grid tanpa menghancurkan activity
        updateGrid();

        // Lanjutkan lagu
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    // Fungsi untuk memperbarui grid stage
    private void updateGrid() {
        // Bersihkan grid dulu sebelum tambah tombol (biar ga double saat onResume)
        stageGrid.removeAllViews();

        // Buat 10 tombol stage
        for (int i = 1; i <= 10; i++) {
            View stageButton = getLayoutInflater().inflate(R.layout.item_stage_button, null);

            TextView stageText = stageButton.findViewById(R.id.stageNumber);
            ImageView lockIcon = stageButton.findViewById(R.id.lockIcon);
            ImageView checkIcon = stageButton.findViewById(R.id.checkIcon);

            stageText.setText("Stage " + i);

            // Logic centang/kunci:
            if (i < maxStageUnlocked) {
                // Stage yang sudah selesai ✅
                lockIcon.setVisibility(View.GONE);
                checkIcon.setVisibility(View.VISIBLE);
            } else if (i == maxStageUnlocked) {
                // Stage yang baru terbuka
                lockIcon.setVisibility(View.GONE);
                checkIcon.setVisibility(View.GONE);
            } else {
                // Stage yang masih terkunci 🔒
                lockIcon.setVisibility(View.VISIBLE);
                checkIcon.setVisibility(View.GONE);
            }

            // Pastikan Stage 10 ditandai dengan benar
            if (i == 10 && maxStageUnlocked >= 10) {
                lockIcon.setVisibility(View.GONE);
                checkIcon.setVisibility(View.VISIBLE); // Tercentang jika stage 10 sudah diselesaikan
            }

            // Atur kolom GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            int column = (i - 1) % 2; // 2 kolom
            params.columnSpec = GridLayout.spec(column, 1f);
            params.width = 0;
            stageButton.setLayoutParams(params);

            final int selectedStage = i;
            stageButton.setOnClickListener(v -> {
                v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
                if (selectedStage <= maxStageUnlocked) {
                    Intent intent = new Intent(ChoosingStageActivity.this, GameChallangeActivity.class);
                    intent.putExtra("stageNumber", selectedStage);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Selesaikan stage sebelumnya dulu!", Toast.LENGTH_SHORT).show();
                }
            });

            stageGrid.addView(stageButton);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
