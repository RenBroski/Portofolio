package com.example.ujianreno;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ujianreno.adapter.VideoHistoryAdapter;
import com.example.ujianreno.database.AppDatabase;
import com.example.ujianreno.database.VideoHistory;
import com.example.ujianreno.databinding.ActivityHistoryBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {
    private ActivityHistoryBinding binding;
    private VideoHistoryAdapter adapter;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        executorService = Executors.newSingleThreadExecutor();
        setupRecyclerView();
        loadHistory();
    }

    private void setupRecyclerView() {
        adapter = new VideoHistoryAdapter();
        binding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.historyRecyclerView.setAdapter(adapter);
    }

    private void loadHistory() {
        executorService.execute(() -> {
            List<VideoHistory> history = AppDatabase.getInstance(this)
                    .videoHistoryDao()
                    .getAllHistory();

            runOnUiThread(() -> adapter.setHistory(history));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_clear_history) {
            clearHistory();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearHistory() {
        executorService.execute(() -> {
            AppDatabase.getInstance(this)
                    .videoHistoryDao()
                    .deleteAll();
            runOnUiThread(() -> adapter.setHistory(new ArrayList<>()));
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
} 