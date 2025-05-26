package com.example.ujianreno.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ujianreno.DetailActivity;
import com.example.ujianreno.R;
import com.example.ujianreno.model.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<Video> videos;

    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    public void updateList(List<Video> newList) {
        this.videos = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.titleText.setText(video.getTitle());
        
        Glide.with(holder.itemView.getContext())
                .load(video.getThumbnailUrl())
                .centerCrop()
                .into(holder.thumbnailImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("video_id", video.getId());
            intent.putExtra("video_title", video.getTitle());
            intent.putExtra("video_thumbnail", video.getThumbnailUrl());
            intent.putExtra("video_url", video.getVideoUrl());
            intent.putExtra("video_description", video.getDescription());
            intent.putExtra("video_rating", video.getRating());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView titleText;

        VideoViewHolder(View itemView) {
            super(itemView);
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
            titleText = itemView.findViewById(R.id.titleText);
        }
    }
} 