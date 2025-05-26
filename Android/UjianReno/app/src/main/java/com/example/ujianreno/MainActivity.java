package com.example.ujianreno;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ujianreno.adapter.PopularVideoAdapter;
import com.example.ujianreno.adapter.NewReleaseAdapter;
import com.example.ujianreno.databinding.ActivityMainBinding;
import com.example.ujianreno.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PopularVideoAdapter popularVideosAdapter;
    private NewReleaseAdapter newReleasesAdapter;
    private List<Video> allVideos;
    private List<Video> popularVideos;
    private List<Video> newReleases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        
        setupProfileImage();
        initializeVideoLists();
        setupRecyclerViews();
        setupBottomNavigation();
        setupSearchButton();
    }

    private void initializeVideoLists() {
        allVideos = new ArrayList<>();
        popularVideos = new ArrayList<>();
        newReleases = new ArrayList<>();
        
        // Menambahkan semua video
        addAllVideos();
        
        // Memisahkan video berdasarkan rating untuk Popular Videos
        for (Video video : allVideos) {
            if (video.getRating() >= 4.5) {
                popularVideos.add(video);
            }
        }

        // Mengambil 5 video terakhir untuk New Releases
        int startIndex = Math.max(0, allVideos.size() - 5);
        for (int i = startIndex; i < allVideos.size(); i++) {
            newReleases.add(allVideos.get(i));
        }
    }

    private void setupRecyclerViews() {
        // Setup Popular Videos RecyclerView
        popularVideosAdapter = new PopularVideoAdapter(popularVideos);
        binding.popularVideosRecyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        binding.popularVideosRecyclerView.setAdapter(popularVideosAdapter);

        // Setup New Releases RecyclerView
        newReleasesAdapter = new NewReleaseAdapter(newReleases);
        binding.newReleasesRecyclerView.setLayoutManager(
            new LinearLayoutManager(this)
        );
        binding.newReleasesRecyclerView.setAdapter(newReleasesAdapter);
    }

    private void addAllVideos() {
        // Menambahkan semua video seperti sebelumnya
        allVideos.add(new Video(
            "1",
            "Big Buck Bunny",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "Big Buck Bunny bercerita tentang seekor kelinci besar dan baik hati yang menghadapi tiga pengganggu yang mengusik ketenangan hidupnya.",
            4.8f
        ));

        allVideos.add(new Video(
            "2",
            "Elephant Dream",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "Film animasi open source pertama di dunia dengan visual yang menakjubkan.",
            4.5f
        ));

        allVideos.add(new Video(
            "3",
            "For Bigger Blazes",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "Video demonstrasi dengan efek visual api yang menakjubkan.",
            4.3f
        ));

        allVideos.add(new Video(
            "4",
            "For Bigger Escapes",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            "Adegan aksi menegangkan dengan scene pengejaran yang seru.",
            4.6f
        ));

        allVideos.add(new Video(
            "5",
            "For Bigger Fun",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            "Kompilasi momen menyenangkan dan menghibur.",
            4.7f
        ));

        allVideos.add(new Video(
            "6",
            "For Bigger Joyrides",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            "Petualangan seru dengan kendaraan yang menakjubkan.",
            4.4f
        ));

        allVideos.add(new Video(
            "7",
            "For Bigger Meltdowns",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerMeltdowns.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            "Aksi komedi yang menghibur dan penuh kejutan.",
            4.2f
        ));

        allVideos.add(new Video(
            "8",
            "Sintel",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            "Film animasi epik tentang petualangan seorang gadis mencari naga.",
            4.9f
        ));

        allVideos.add(new Video(
            "9",
            "Subaru Outback On Street And Dirt",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            "Aksi mobil Subaru Outback di berbagai medan.",
            4.5f
        ));

        allVideos.add(new Video(
            "10",
            "Tears of Steel",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/TearsOfSteel.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            "Film sci-fi dengan efek visual yang menakjubkan.",
            4.7f
        ));

        // Tambahkan video baru
        allVideos.add(new Video(
            "11",
            "Volkswagen GTI Review",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/VolkswagenGTIReview.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
            "Review mendalam tentang Volkswagen GTI dengan performa tinggi.",
            4.6f
        ));

        allVideos.add(new Video(
            "12",
            "We Are Going On Bullrun",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/images/WeAreGoingOnBullrun.jpg",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            "Dokumenter seru tentang perjalanan Bullrun yang mendebarkan.",
            4.7f
        ));
    }

    private void setupBottomNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_history) {
                // Pindah ke HistoryActivity
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            }
            return false;
        });
    }

    private void setupSearchButton() {
        binding.searchButton.setOnClickListener(v -> toggleSearchBar());
        
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchVideos(newText);
                return true;
            }
        });

        // Sembunyikan searchView saat awal
        binding.searchView.setVisibility(View.GONE);
    }

    private void toggleSearchBar() {
        if (binding.searchView.getVisibility() == View.VISIBLE) {
            binding.searchView.setVisibility(View.GONE);
            binding.searchView.setQuery("", false);
            resetVideoLists();
        } else {
            binding.searchView.setVisibility(View.VISIBLE);
            binding.searchView.setIconified(false); // Langsung tampilkan keyboard
            binding.searchView.requestFocus();
        }
    }

    private void resetVideoLists() {
        popularVideosAdapter.updateList(popularVideos);
        newReleasesAdapter.updateList(newReleases);
    }

    private void searchVideos(String query) {
        List<Video> filteredPopular = new ArrayList<>();
        List<Video> filteredNew = new ArrayList<>();

        String lowercaseQuery = query.toLowerCase();

        // Filter Popular Videos
        for (Video video : popularVideos) {
            if (video.getTitle().toLowerCase().contains(lowercaseQuery) ||
                video.getDescription().toLowerCase().contains(lowercaseQuery)) {
                filteredPopular.add(video);
            }
        }

        // Filter New Releases
        for (Video video : newReleases) {
            if (video.getTitle().toLowerCase().contains(lowercaseQuery) ||
                video.getDescription().toLowerCase().contains(lowercaseQuery)) {
                filteredNew.add(video);
            }
        }

        popularVideosAdapter.updateList(filteredPopular);
        newReleasesAdapter.updateList(filteredNew);
    }

    private void setupProfileImage() {
        ImageView profileImageView = binding.toolbar.findViewById(R.id.navProfileImage);
        
        Glide.with(this)
            .load(R.drawable.profile)
            .circleCrop()
            .into(profileImageView);
    }
} 