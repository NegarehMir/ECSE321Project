package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;


public class CreatePlaylist extends AppCompatActivity {

    private Integer selectedSong = -1;
    private HashMap<Integer, Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PersistenceHomeAudioSystem.setFileName(getFilesDir().getAbsolutePath() + "/" + "homeAudioSystem.xml");
        PersistenceHomeAudioSystem.loadHomeAudioSystemModel();



        refreshData();
    }

    private void refreshData(){

        HomeAudioSystem has = HomeAudioSystem.getInstance();

        TextView tv = (TextView) findViewById(R.id.newplaylist_name);
        tv.setText("");

        HomeAudioSystemController hasc = new HomeAudioSystemController();

        LinkedList<Song> allSongsInLibrary = hasc.getAllSongsFromLibrary();

        Spinner spinner = (Spinner) findViewById(R.id.newplaylist_songspinner);
        ArrayAdapter<CharSequence> songsAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        songsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        songs = new HashMap<Integer, Song>();

        for (Song song : allSongsInLibrary) {
            songs.put(songs.size(), song);
            songsAdapter.add(song.getTitle() + " - " + song.getArtist(0).getName());
        }
        selectedSong = -1;
        //songList.setSelectedIndex(selectedSong);

        spinner.setAdapter(songsAdapter);

    }

}
