package com.example.ujianreno;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ujianreno.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupProfileImage();
        setupAnimations();
        setupClickListeners();
    }

    private void setupProfileImage() {
        // Muat gambar profile menggunakan Glide
        Glide.with(this)
            .load(R.drawable.profile)
            .circleCrop()
            .into(binding.profileImage);
    }

    private void setupAnimations() {
        // Animasi untuk profile image
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        binding.profileImage.startAnimation(slideUp);

        // Animasi untuk button
        Animation slideFromBottom = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
        binding.btnStart.startAnimation(slideFromBottom);
    }

    private void setupClickListeners() {
        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
} 