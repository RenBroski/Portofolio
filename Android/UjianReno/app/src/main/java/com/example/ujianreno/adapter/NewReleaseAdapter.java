// Implementasi adapter untuk new releases dengan layout item_new_release.xml 

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

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.NewReleaseViewHolder> {
    private List<Video> videos;

    public NewReleaseAdapter(List<Video> videos) {
        this.videos = videos;
    }

    public void updateList(List<Video> newList) {
        this.videos = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_new_release, parent, false);
        return new NewReleaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleaseViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.titleText.setText(video.getTitle());
        holder.descriptionText.setText(video.getDescription());
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

    static class NewReleaseViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView titleText;
        TextView descriptionText;
        TextView ratingText;

        NewReleaseViewHolder(View itemView) {
            super(itemView);
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            ratingText = itemView.findViewById(R.id.ratingText);
        }
    }
} 