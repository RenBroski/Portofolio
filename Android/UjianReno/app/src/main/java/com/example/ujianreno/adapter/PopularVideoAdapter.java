// Implementasi adapter untuk popular videos dengan layout item_popular_video.xml 

package com.example.ujianreno.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ujianreno.DetailActivity;
import com.example.ujianreno.R;
import com.example.ujianreno.model.Video;

import java.util.List;

public class PopularVideoAdapter extends RecyclerView.Adapter<PopularVideoAdapter.PopularVideoViewHolder> {
    private List<Video> videos;

    public PopularVideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    public void updateList(List<Video> newList) {
        this.videos = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_video, parent, false);
        return new PopularVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularVideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.titleText.setText(video.getTitle());
        holder.ratingBar.setRating(video.getRating());
        holder.ratingText.setText(String.format("%.1f", video.getRating()));

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

    static class PopularVideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView titleText;
        RatingBar ratingBar;
        TextView ratingText;
        ImageView playButton;

        PopularVideoViewHolder(View itemView) {
            super(itemView);
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
            titleText = itemView.findViewById(R.id.titleText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingText = itemView.findViewById(R.id.ratingText);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }
} 