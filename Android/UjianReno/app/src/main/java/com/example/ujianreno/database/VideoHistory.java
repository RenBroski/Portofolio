package com.example.ujianreno.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_history")
public class VideoHistory {
    @PrimaryKey
    @NonNull
    private String videoId;
    private String title;
    private String thumbnailUrl;
    private long watchedAt;

    public VideoHistory(@NonNull String videoId, String title, String thumbnailUrl) {
        this.videoId = videoId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.watchedAt = System.currentTimeMillis();
    }

    // Getters
    @NonNull
    public String getVideoId() { return videoId; }
    public String getTitle() { return title; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public long getWatchedAt() { return watchedAt; }

    // Setters
    public void setVideoId(@NonNull String videoId) { this.videoId = videoId; }
    public void setTitle(String title) { this.title = title; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public void setWatchedAt(long watchedAt) { this.watchedAt = watchedAt; }
} 