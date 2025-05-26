package com.example.tebakwarnareno;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private UserDatabase userDatabase;
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.leaderboardRecyclerView);

        userDatabase = UserDatabase.getInstance(this);

        loadLeaderboard();
    }

    private void loadLeaderboard() {
        try {
            List<User> users = userDatabase.userDao().getLeaderboard();
            if (users == null || users.isEmpty()) {
                Toast.makeText(this, "Belum ada data leaderboard.", Toast.LENGTH_SHORT).show();
                users = new ArrayList<>();
            }

            List<LeaderboardEntry> leaderboardEntries = new ArrayList<>();
            for (User user : users) {
                if (user != null && user.name != null) {
                    leaderboardEntries.add(new LeaderboardEntry(user.name, user.score));
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new LeaderboardAdapter(this, leaderboardEntries);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Terjadi kesalahan saat memuat leaderboard.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
