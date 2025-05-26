package com.example.intent_reno;

import static com.example.intent_reno.R.id.SecondActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;

//    variable
    Animation topAnim,bottomAnim;
    ImageView topImage, bottomImage;
    TextView boldImage, smallImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(SecondActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

//        hooks
        topImage = findViewById(R.id.imageView);
        bottomImage = findViewById(R.id.imageView2);
        boldImage = findViewById(R.id.textView);
        smallImage = findViewById(R.id.textView2);

        topImage.setAnimation(topAnim);
        boldImage.setAnimation(topAnim);
        smallImage.setAnimation(bottomAnim);
        bottomImage.setAnimation(bottomAnim);

//        intent
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent link = new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(link);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
