package com.example.intent_reno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view) {
        Intent link = new Intent(MainActivity5.this, MainActivity3.class);
        startActivity(link);
    }

    public void signup(View view) {
        Intent link = new Intent(MainActivity5.this, MainActivity4.class);
        startActivity(link);
    }

    public void takisung(View view) {
        Intent link = new Intent(MainActivity5.this, TakisungActivity.class);
        startActivity(link);
    }

    public void batakan(View view) {
        Intent link = new Intent(MainActivity5.this, BatakanActivity.class);
        startActivity(link);
    }

    public void tahura(View view) {
        Intent link = new Intent(MainActivity5.this, TahuraActivity.class);
        startActivity(link);
    }

    public void turkey(View view) {
        Intent link = new Intent(MainActivity5.this, TurkeyActivity.class);
        startActivity(link);
    }

    public void pagatan(View view) {
        Intent link = new Intent(MainActivity5.this, PagatanActivity.class);
        startActivity(link);
    }

    public void bajuin(View view) {
        Intent link = new Intent(MainActivity5.this, BajuinActivity.class);
        startActivity(link);
    }

    public void angsana(View view) {
        Intent link = new Intent(MainActivity5.this, AngsanaActivity.class);
        startActivity(link);
    }

    public void birah(View view) {
        Intent link = new Intent(MainActivity5.this, BirahActivity.class);
        startActivity(link);
    }

    public void notif(View view) {
        Intent link = new Intent(MainActivity5.this, NotificationActivity.class);
        startActivity(link);
    }
}