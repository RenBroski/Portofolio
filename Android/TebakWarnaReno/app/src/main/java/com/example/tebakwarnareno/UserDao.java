package com.example.tebakwarnareno;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE name = :username LIMIT 1")
    User getUserByName(String username);

    @Query("SELECT * FROM User ORDER BY score DESC")
    List<User> getUsers();

    @Query("UPDATE User SET score = :newScore WHERE name = :username AND :newScore > score")
    void updateScore(String username, int newScore);

    // ✅ Ambil leaderboard
    @Query("SELECT * FROM User ORDER BY score DESC")
    List<User> getLeaderboard();

    // ✅ Reset semua skor
    @Query("UPDATE User SET score = 0")
    void resetLeaderboard();
}

