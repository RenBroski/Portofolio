package com.example.ujianreno;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujianreno.databinding.ActivityVideoPlayerBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    private ActivityVideoPlayerBinding binding;
    private ExoPlayer player;
    private boolean isFullscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String videoUrl = getIntent().getStringExtra("video_url");
        initializePlayer(videoUrl);
        setupPlayerControls();
    }

    private void initializePlayer(String videoUrl) {
        player = new ExoPlayer.Builder(this).build();
        binding.playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        // Tambahkan listener untuk update UI kontrol
        player.addListener(new Player.Listener() {
            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                updatePlayPauseButton(isPlaying);
            }
        });
    }

    private void setupPlayerControls() {
        // Setup tombol play/pause
        View playPauseButton = binding.playerView.findViewById(com.google.android.exoplayer2.ui.R.id.exo_play);
        View pauseButton = binding.playerView.findViewById(com.google.android.exoplayer2.ui.R.id.exo_pause);

        if (playPauseButton != null) {
            playPauseButton.setOnClickListener(v -> {
                if (!player.isPlaying()) {
                    player.play();
                }
            });
        }

        if (pauseButton != null) {
            pauseButton.setOnClickListener(v -> {
                if (player.isPlaying()) {
                    player.pause();
                }
            });
        }

        // Setup tombol fullscreen
        ImageView fullscreenButton = binding.playerView.findViewById(com.google.android.exoplayer2.ui.R.id.exo_fullscreen);
        if (fullscreenButton != null) {
            fullscreenButton.setOnClickListener(v -> toggleFullscreen());
        }
    }

    private void updatePlayPauseButton(boolean isPlaying) {
        View playButton = binding.playerView.findViewById(com.google.android.exoplayer2.ui.R
                .id.exo_play);
        View pauseButton = binding.playerView.findViewById(com.google.android.exoplayer2.ui.R.id.exo_pause);

        if (playButton != null && pauseButton != null) {
            playButton.setVisibility(isPlaying ? View.GONE : View.VISIBLE);
            pauseButton.setVisibility(isPlaying ? View.VISIBLE : View.GONE);
        }
    }

    private void toggleFullscreen() {
        if (isFullscreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        isFullscreen = !isFullscreen;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isFullscreen = false;
        } else {
            super.onBackPressed();
        }
    }
} 