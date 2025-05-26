package com.example.tebakwarnareno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<LeaderboardEntry> leaderboardList;
    private Context context;

    public LeaderboardAdapter(Context context, List<LeaderboardEntry> leaderboardList) {
        this.context = context;
        this.leaderboardList = leaderboardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_leaderboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LeaderboardEntry entry = leaderboardList.get(position);

        // Set the username and score
        holder.usernameText.setText(entry.username);
        holder.scoreText.setText("Skor: " + entry.score);

        // Set a placeholder avatar image (you can change it to something dynamic later)
        holder.avatarImage.setImageResource(R.drawable.home); // Placeholder image
    }

    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText, scoreText;
        ImageView avatarImage;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.usernameText);
            scoreText = itemView.findViewById(R.id.scoreText);
            avatarImage = itemView.findViewById(R.id.avatarImage);
        }
    }
}
