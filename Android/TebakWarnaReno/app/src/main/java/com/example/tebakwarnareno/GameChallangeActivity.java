package com.example.tebakwarnareno;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameChallangeActivity extends AppCompatActivity {

    private TextView instructionText, timerText;
    private EditText answerInput;
    private Button submitButton;
    private int stageNumber;
    private CountDownTimer timer;

    private String[] colors = {"merah", "hijau", "biru", "kuning", "ungu", "pink", "coklat", "abu abu", "hitam", "putih"};
    private String[] descriptions = {
            "Warna ini melambangkan keberanian dan sering terlihat di bendera Indonesia",
            "Warna ini sering kita lihat pada daun dan rumput",
            "Warna ini ada di langit cerah tanpa awan",
            "Warna ini seperti cahaya matahari",
            "Warna ini campuran merah dan biru, sering diasosiasikan dengan kemewahan",
            "Warna ini sering diasosiasikan dengan cinta dan bunga mawar muda",
            "Warna ini seperti batang pohon atau tanah",
            "Warna ini campuran antara hitam dan putih",
            "Warna ini gelap pekat tanpa cahaya",
            "Warna ini bersih seperti kapas"
    };

    private String correctAnswer;
    private int timeLimitMs = 10000; // 10 detik default

    private MediaPlayer backSoundPlayer, rightSoundPlayer, loseSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_challange);

        instructionText = findViewById(R.id.instructionText);
        timerText = findViewById(R.id.timerText);
        answerInput = findViewById(R.id.answerInput);
        submitButton = findViewById(R.id.submitButton);

        stageNumber = getIntent().getIntExtra("stageNumber", 1);
        timeLimitMs = 10000 - ((stageNumber - 1) * 800);

        // 🔊 Load sound
        backSoundPlayer = MediaPlayer.create(this, R.raw.backsoundgame);
        rightSoundPlayer = MediaPlayer.create(this, R.raw.rightasnwer);
        loseSoundPlayer = MediaPlayer.create(this, R.raw.lose);

        backSoundPlayer.setLooping(true);
        backSoundPlayer.start();

        startGame();

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        submitButton.setOnClickListener(v -> {
            checkAnswer();
        });
    }

    private void startGame() {
        if (stageNumber <= descriptions.length) {
            instructionText.setText(descriptions[stageNumber - 1]);
            correctAnswer = colors[stageNumber - 1]; // jawaban benar sesuai stage
            answerInput.setText("");
            startTimer();

            // 🔊 Start backsound kalau belum main
            if (!backSoundPlayer.isPlaying()) {
                backSoundPlayer.start();
            }
        }
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLimitMs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("" + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                stopBackSound(); // Stop backsound saat game over
                playLoseSound();
                showGameOverDialog();
            }
        }.start();
    }

    private void checkAnswer() {
        timer.cancel();

        String userAnswer = answerInput.getText().toString().trim().toLowerCase();

        if (userAnswer.equals(correctAnswer)) {
            playRightSound();
            unlockNextStage();

            if (stageNumber == 10) {
                stopBackSound(); // Stop backsound saat tamat
                Intent intent = new Intent(GameChallangeActivity.this, CongratulationsActivity.class);
                startActivity(intent);
                finish();
            } else {
                showCorrectDialog();
            }

        } else {
            stopBackSound(); // Stop backsound saat jawaban salah
            playLoseSound();
            showGameOverDialog();
        }
    }

    private void showGameOverDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Waktu habis atau jawaban salah!")
                .setPositiveButton("Coba Lagi", (dialog, which) -> {
                    startGame(); // Mulai lagi stage
                })
                .setNegativeButton("Kembali", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void showCorrectDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Jawaban Benar!")
                .setMessage("Selamat, lanjut ke stage berikutnya.")
                .setPositiveButton("Continue Stage", (dialog, which) -> {
                    Intent intent = new Intent(GameChallangeActivity.this, GameChallangeActivity.class);
                    intent.putExtra("stageNumber", stageNumber + 1);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .show();
    }

    private void unlockNextStage() {
        SharedPreferences prefs = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        int maxStage = prefs.getInt("maxStageUnlocked", 1);
        if (stageNumber >= maxStage && stageNumber < 10) {
            prefs.edit().putInt("maxStageUnlocked", stageNumber + 1).apply();
        }
    }

    private void playRightSound() {
        if (rightSoundPlayer != null) {
            rightSoundPlayer.start();
        }
    }

    private void playLoseSound() {
        if (loseSoundPlayer != null) {
            loseSoundPlayer.start();
        }
    }

    private void stopBackSound() {
        if (backSoundPlayer != null && backSoundPlayer.isPlaying()) {
            backSoundPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        if (backSoundPlayer != null) {
            backSoundPlayer.stop();
            backSoundPlayer.release();
        }
        if (rightSoundPlayer != null) {
            rightSoundPlayer.release();
        }
        if (loseSoundPlayer != null) {
            loseSoundPlayer.release();
        }
    }
}
