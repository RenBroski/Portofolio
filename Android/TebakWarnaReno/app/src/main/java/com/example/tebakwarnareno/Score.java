package com.example.tebakwarnareno;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int highscore;

    public Score(int highscore) {
        this.highscore = highscore;
    }
}
