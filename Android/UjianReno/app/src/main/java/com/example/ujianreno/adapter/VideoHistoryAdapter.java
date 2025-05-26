package com.example.ujianreno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ujianreno.R;
import com.example.ujianreno.database.VideoHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VideoHistoryAdapter extends RecyclerView.Adapter<VideoHistoryAdapter.HistoryViewHolder> {
    private List<VideoHistory> historyList = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm", new Locale("id", "ID"));

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        VideoHistory history = historyList.get(position);
        holder.titleText.setText(history.getTitle());
        holder.dateText.setText(dateFormat.format(new Date(history.getWatchedAt())));

        Glide.with(holder.itemView.getContext())
                .load(history.getThumbnailUrl())
                .centerCrop()
                .into(holder.thumbnailImage);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setHistory(List<VideoHistory> history) {
        this.historyList = history;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView titleText;
        TextView dateText;

        HistoryViewHolder(View itemView) {
            super(itemView);
            thumbnailImage = itemView.findViewById(R.id.historyThumbnail);
            titleText = itemView.findViewById(R.id.historyTitle);
            dateText = itemView.findViewById(R.id.historyDate);
        }
    }
} 