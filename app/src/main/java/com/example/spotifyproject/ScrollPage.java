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
    //private TextView songView = findViewById(R.id.song1);
    //private TextView artistView;


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


        //TextView userName = findViewById(R.id.username);
        //userName.setText(username);


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
        if (topTracks.size() > 4) {
            TextView song1 = findViewById(R.id.song1);
            song1.setText(topTracks.get(0).getName());
            song = topTracks.get(0);
            TextView song2 = findViewById(R.id.song2);
            song2.setText(topTracks.get(1).getName());
            song = topTracks.get(1);
            TextView song3 = findViewById(R.id.song3);
            song3.setText(topTracks.get(2).getName());
            song = topTracks.get(2);
            TextView song4 = findViewById(R.id.song4);
            song4.setText(topTracks.get(3).getName());
            song = topTracks.get(3);
            TextView song5 = findViewById(R.id.song5);
            song5.setText(topTracks.get(4).getName());
            song = topTracks.get(4);

        }
    }

    private void getArtists() {
        artistService.getTopArtists(() -> {
            topArtists = artistService.getArtists();
            updateArtist();
        });
    }

    private void updateArtist() {
        if (topArtists.size() > 4) {
            TextView artist1 = findViewById(R.id.artist1);
            artist1.setText(topArtists.get(0).getName());
            artist = topArtists.get(0);
            TextView artist2 = findViewById(R.id.artist2);
            artist2.setText(topArtists.get(1).getName());
            artist = topArtists.get(1);
            TextView artist3 = findViewById(R.id.artist3);
            artist3.setText(topArtists.get(2).getName());
            artist = topArtists.get(2);
            TextView artist4 = findViewById(R.id.artist4);
            artist4.setText(topArtists.get(3).getName());
            artist = topArtists.get(3);
            TextView artist5 = findViewById(R.id.artist5);
            artist5.setText(topArtists.get(4).getName());
            artist = topArtists.get(4);
        }
    }



}