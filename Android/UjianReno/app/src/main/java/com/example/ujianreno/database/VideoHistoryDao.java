package com.example.ujianreno.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(VideoHistory videoHistory);

    @Query("SELECT * FROM video_history ORDER BY watchedAt DESC")
    List<VideoHistory> getAllHistory();

    @Query("DELETE FROM video_history WHERE videoId = :videoId")
    void deleteById(String videoId);

    @Query("DELETE FROM video_history")
    void deleteAll();
} 