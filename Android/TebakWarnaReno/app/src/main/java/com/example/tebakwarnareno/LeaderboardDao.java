package com.example.tebakwarnareno;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LeaderboardDao {

    // Tambah user baru
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(LeaderboardEntry user);

    // Ambil semua user, urut skor dari terbesar
    @Query("SELECT * FROM LeaderboardEntry ORDER BY score DESC")
    List<LeaderboardEntry> getLeaderboard();

    // Cari user berdasarkan nama
    @Query("SELECT * FROM LeaderboardEntry WHERE username = :username LIMIT 1")
    LeaderboardEntry getUserByName(String username);

    // Update skor user
    @Query("UPDATE LeaderboardEntry SET score = :newScore WHERE username = :username")
    void updateScore(String username, int newScore);

    // Reset leaderboard (set skor jadi 0 semua)
    @Query("UPDATE LeaderboardEntry SET score = 0")
    void resetLeaderboard();
}
