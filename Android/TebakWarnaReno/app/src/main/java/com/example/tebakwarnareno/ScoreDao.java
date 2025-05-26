package com.example.tebakwarnareno;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ScoreDao {

    @Insert
    void insert(Score score);

    @Query("SELECT MAX(highscore) FROM Score")
    int getHighScore();
}
