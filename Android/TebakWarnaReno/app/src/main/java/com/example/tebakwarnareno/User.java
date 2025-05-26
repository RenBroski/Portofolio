package com.example.tebakwarnareno;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public int score;

    // Konstruktor untuk user baru (skor default 0)
    public User(String name) {
        this.name = name;
        this.score = 0; // Default score
    }

    // Konstruktor lengkap (kalau mau, ditandai dengan @Ignore karena tidak digunakan oleh Room)
    @Ignore
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
