package com.example.ujianreno;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ujianreno.database.AppDatabase;
import com.example.ujianreno.database.VideoHistory;
import com.example.ujianreno.databinding.ActivityDetailBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        executorService = Executors.newSingleThreadExecutor();

        // Ambil data dari intent
        String videoId = getIntent().getStringExtra("video_id");
        String title = getIntent().getStringExtra("video_title");
        String thumbnailUrl = getIntent().getStringExtra("video_thumbnail");
        String videoUrl = getIntent().getStringExtra("video_url");
        String description = getIntent().getStringExtra("video_description");
        float rating = getIntent().getFloatExtra("video_rating", 0f);

        updateUI(title, thumbnailUrl, description, rating);
        saveToHistory(videoId, title, thumbnailUrl);
        setupPlayButton(videoUrl);
    }

    private void updateUI(String title, String thumbnailUrl, String description, float rating) {
        binding.titleText.setText(title);
        binding.descriptionText.setText(description);
        binding.ratingBar.setRating(rating);

        Glide.with(this)
            .load(thumbnailUrl)
            .centerCrop()
            .into(binding.thumbnailImage);
    }

    private void saveToHistory(String videoId, String title, String thumbnailUrl) {
        executorService.execute(() -> {
            VideoHistory history = new VideoHistory(videoId, title, thumbnailUrl);
            AppDatabase.getInstance(this)
                .videoHistoryDao()
                .insert(history);
        });
    }

    private void setupPlayButton(String videoUrl) {
        binding.playButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("video_url", videoUrl);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
} 