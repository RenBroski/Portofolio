package com.example.ujianreno.model;

import java.io.Serializable;

public class Video implements Serializable {
    private String id;
    private String title;
    private String thumbnailUrl;
    private String videoUrl;
    private String description;
    private float rating;

    public Video(String id, String title, String thumbnailUrl, String videoUrl, String description, float rating) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.description = description;
        this.rating = rating;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public String getVideoUrl() { return videoUrl; }
    public String getDescription() { return description; }
    public float getRating() { return rating; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public void setDescription(String description) { this.description = description; }
    public void setRating(float rating) { this.rating = rating; }
} 