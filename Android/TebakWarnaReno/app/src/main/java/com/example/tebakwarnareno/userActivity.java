package com.example.tebakwarnareno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class userActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button startButton;
    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameInput = findViewById(R.id.nameInput);
        startButton = findViewById(R.id.startButton);
        userDatabase = UserDatabase.getInstance(this);

        startButton.setOnClickListener(v -> {
            String username = nameInput.getText().toString().trim();

            if (!username.isEmpty()) {
                User existingUser = userDatabase.userDao().getUserByName(username);

                if (existingUser == null) {
                    userDatabase.userDao().insertUser(new User(username));
                }

                SharedPreferences prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                prefs.edit().putString("username", username).apply();

                Toast.makeText(this, "Selamat datang, " + username + "!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, HomeScreenActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Masukkan nama dulu ya!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
