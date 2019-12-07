package com.example.spotifyproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.spotifyproject.Connectors.ArtistService;
import com.example.spotifyproject.Connectors.SongService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollPage extends AppCompatActivity {

    private TextView userView;
    private TextView songView;
    private TextView tempArtist;
    private Song song;
    private Artist artist;

    private SongService songService;
    private ArrayList<Song> recentlyPlayedTracks;
    private ArtistService artistService;
    private ArrayList<Artist> topArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_page);

        songService = new SongService(getApplicationContext());
        userView = findViewById(R.id.artist1);
        songView = findViewById(R.id.song1);

        tempArtist = findViewById(R.id.artist2);


        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);

        userView.setText(sharedPreferences.getString("userid", "No User"));

        //getTracks();
        getArtists();
    }


    private void getTracks() {
        songService.getTopSongsCall(() -> {
            recentlyPlayedTracks = songService.getTopSongs();
            updateSong();
        });
    }

    private void updateSong() {
        if (recentlyPlayedTracks.size() > 0) {
            songView.setText(recentlyPlayedTracks.get(0).getName());
            songView.setVisibility(View.VISIBLE);
            song = recentlyPlayedTracks.get(0);
        }
    }

    private void getArtists() {
        artistService.getTopArtists(() -> {
            topArtists = artistService.getArtists();
            updateArtists();
        });
    }

    private void updateArtists() {
        if (topArtists.size() > 0) {
            tempArtist.setText(topArtists.get(0).getName());

        }
    }



}