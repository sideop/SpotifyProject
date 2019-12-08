package com.example.spotifyproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.spotifyproject.Connectors.ArtistService;
import com.example.spotifyproject.Connectors.SongService;
import com.example.spotifyproject.Connectors.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * THIS CLASS DISPLAYS USERS TOP SONGS AND ARTISTS IN AN INFLATED VIEW
 */
public class ScrollPage extends AppCompatActivity {

    private TextView userView;


    /**
     * current views, need to be reassigned and become inflated views. see below.
     */
    private TextView songView;
    private TextView artistView;

    private Song song;
    private Artist artist;

    private SongService songService;
    private ArrayList<Song> topTracks;
    private ArtistService artistService;
    private ArrayList<Artist> topArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_page);

        songService = new SongService(getApplicationContext());
        artistService = new ArtistService(getApplicationContext());


        /**
         * displays username.
         */
        userView = findViewById(R.id.userProfile);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        String username = sharedPreferences.getString("userid", "No User");
        userView.setText(username + "'s profile");


        //songView = findViewById(R.id.song1);
        //artistView = findViewById(R.id.artist1);

        /** make inflator view and assign each song and artist, which are in ArrayLists
         * the arraylists names are declared above and filled when getTracks and getArtists is called
         * just put the first 5 songs and artists for now.
         * YOUR CODE HERE
         */

        getTracks();
        getArtists();

    }


    private void getTracks() {
        songService.getTopSongsCall(() -> {
            topTracks = songService.getTopSongs();
            updateSong();
        });
    }

    private void updateSong() {
        if (topTracks.size() > 0) {
            songView.setText(topTracks.get(0).getName());
            songView.setVisibility(View.VISIBLE);
            song = topTracks.get(0);
        }
    }

    private void getArtists() {
        artistService.getTopArtists(() -> {
            topArtists = artistService.getArtists();
            updateArtist();
        });
    }

    private void updateArtist() {
        if (topArtists.size() > 0) {
            artistView.setText(topArtists.get(0).getName());
            artist = topArtists.get(0);
        }
    }



}