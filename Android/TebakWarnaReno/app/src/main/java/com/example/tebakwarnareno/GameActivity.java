package com.example.tebakwarnareno;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private UserDatabase userDatabase;
    private TextView countdownText, scoreText, highScoreText;
    private EditText answerInput;
    private Button checkButton;
    private View timerBar, colorBox;

    private int score = 0;
    private int highScore = 0;
    private CountDownTimer gameTimer;

    private List<String> colors;
    private String currentColor;

    private AppDatabase db;
    private int screenWidth;

    private MediaPlayer countdownPlayer, backsoundPlayer, rightAnswerPlayer, losePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        userDatabase = UserDatabase.getInstance(this);

        countdownText = findViewById(R.id.countdownText);
        colorBox = findViewById(R.id.colorBox);
        scoreText = findViewById(R.id.scoreText);
        highScoreText = findViewById(R.id.highScoreText);
        answerInput = findViewById(R.id.answerInput);
        checkButton = findViewById(R.id.checkButton);
        timerBar = findViewById(R.id.timerBar);

        screenWidth = getResources().getDisplayMetrics().widthPixels;

        db = AppDatabase.getInstance(this);
        highScore = db.scoreDao().getHighScore();
        highScoreText.setText("Highscore: " + highScore);

        initColors();
        showGameElements(false);
        startCountdown();

        checkButton.setOnClickListener(v -> checkAnswer());
    }

    private void initColors() {
        colors = new ArrayList<>();
        Collections.addAll(colors, "Merah", "Biru", "Hijau", "Kuning", "Ungu", "Oranye", "Pink", "Coklat", "Hitam", "Abu-abu");
    }

    private void showGameElements(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        colorBox.setVisibility(visibility);
        scoreText.setVisibility(visibility);
        highScoreText.setVisibility(visibility);
        answerInput.setVisibility(visibility);
        checkButton.setVisibility(visibility);
        timerBar.setVisibility(visibility);
    }

    private void startCountdown() {
        countdownPlayer = MediaPlayer.create(this, R.raw.countdown);
        countdownPlayer.start();

        new CountDownTimer(3000, 1000) {
            int count = 3;

            public void onTick(long millisUntilFinished) {
                countdownText.setText(String.valueOf(count));
                Animation zoom = AnimationUtils.loadAnimation(GameActivity.this, R.anim.zoom);
                countdownText.startAnimation(zoom);
                count--;
            }

            public void onFinish() {
                countdownText.setVisibility(View.GONE);
                stopPlayer(countdownPlayer);
                countdownPlayer = null;

                backsoundPlayer = MediaPlayer.create(GameActivity.this, R.raw.backsoundgame);
                backsoundPlayer.setLooping(true);
                backsoundPlayer.start();

                showGameElements(true);
                nextColor();
            }
        }.start();
    }

    private void nextColor() {
        answerInput.setText("");
        Collections.shuffle(colors);
        for (String color : colors) {
            if (!color.equals(currentColor)) {
                currentColor = color;
                break;
            }
        }

        colorBox.setBackgroundColor(getColorFromName(currentColor));
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.slide);
        colorBox.startAnimation(slide);
        startTimer();
    }

    private int getColorFromName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "merah": return Color.RED;
            case "biru": return Color.BLUE;
            case "hijau": return Color.GREEN;
            case "kuning": return Color.YELLOW;
            case "ungu": return Color.parseColor("#800080");
            case "oranye": return Color.parseColor("#FFA500");
            case "pink": return Color.parseColor("#FFC0CB");
            case "coklat": return Color.parseColor("#8B4513");
            case "hitam": return Color.BLACK;
            case "abu-abu": return Color.GRAY;
            default: return Color.WHITE;
        }
    }

    private void startTimer() {
        if (gameTimer != null) gameTimer.cancel();

        timerBar.setBackgroundColor(Color.parseColor("#2196F3"));
        timerBar.getLayoutParams().width = screenWidth;
        timerBar.requestLayout();

        ValueAnimator widthAnim = ValueAnimator.ofInt(screenWidth, 0);
        widthAnim.setDuration(10000);
        widthAnim.addUpdateListener(animation -> {
            int val = (int) animation.getAnimatedValue();
            timerBar.getLayoutParams().width = val;
            timerBar.requestLayout();
            float fraction = (float) val / screenWidth;
            int color = (fraction > 0.3f) ? Color.parseColor("#2196F3") : Color.parseColor("#F44336");
            timerBar.setBackgroundColor(color);
        });
        widthAnim.start();

        gameTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    private void checkAnswer() {
        String userAnswer = answerInput.getText().toString().trim();

        if (userAnswer.equalsIgnoreCase(currentColor)) {
            gameTimer.cancel();

            int barWidth = timerBar.getWidth();
            float fraction = (float) barWidth / screenWidth;
            int points = Math.max(1, (int) (10 * fraction));

            score += points;
            scoreText.setText("Score: " + score);

            stopPlayer(rightAnswerPlayer);
            rightAnswerPlayer = MediaPlayer.create(this, R.raw.rightasnwer);
            rightAnswerPlayer.start();

            nextColor();
        } else {
            gameOver();
        }
    }

    private void gameOver() {
        if (gameTimer != null) gameTimer.cancel();
        stopPlayer(backsoundPlayer);
        backsoundPlayer = null;

        stopPlayer(losePlayer);
        losePlayer = MediaPlayer.create(this, R.raw.lose);
        losePlayer.start();

        String username = getUsernameFromPref();
        if (!username.isEmpty()) {
            User existingUser = userDatabase.userDao().getUserByName(username);
            if (existingUser != null) {
                if (score > existingUser.score) {
                    userDatabase.userDao().updateScore(username, score);
                }
            } else {
                Toast.makeText(this, "User tidak ditemukan!", Toast.LENGTH_SHORT).show();
            }
        }

        if (score > highScore) {
            db.scoreDao().insert(new Score(score));
            highScoreText.setText("Highscore: " + score);
        }

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_gameover, null);
        TextView finalScore = dialogView.findViewById(R.id.finalScore);
        Button homeBtn = dialogView.findViewById(R.id.homeButton);
        Button retryBtn = dialogView.findViewById(R.id.retryButton);

        finalScore.setText("Score kamu: " + score);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        dialog.show();

        Animation bounceFade = AnimationUtils.loadAnimation(this, R.anim.bounce_fade);
        dialogView.startAnimation(bounceFade);

        homeBtn.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(GameActivity.this, HomeScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        retryBtn.setOnClickListener(v -> {
            dialog.dismiss();
            score = 0;
            scoreText.setText("Score: 0");
            stopPlayer(losePlayer);
            losePlayer = null;

            backsoundPlayer = MediaPlayer.create(GameActivity.this, R.raw.backsoundgame);
            backsoundPlayer.setLooping(true);
            backsoundPlayer.start();
            nextColor();
        });
    }

    private String getUsernameFromPref() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return prefs.getString("username", "");
    }

    private void stopPlayer(MediaPlayer player) {
        if (player != null) {
            try {
                if (player.isPlaying()) {
                    player.stop();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } finally {
                try {
                    player.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayer(countdownPlayer);
        stopPlayer(backsoundPlayer);
        stopPlayer(rightAnswerPlayer);
        stopPlayer(losePlayer);

        countdownPlayer = null;
        backsoundPlayer = null;
        rightAnswerPlayer = null;
        losePlayer = null;
    }
}
