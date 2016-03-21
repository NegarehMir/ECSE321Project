package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;

public class AlbumPage extends AppCompatActivity {
    private HashMap<Integer, ca.mcgill.ecse321.group01.homeaudiosystem.model.Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        PersistenceHomeAudioSystem.setFileName(getFilesDir().getAbsolutePath() + "/" + "homeAudioSystem.xml");
        PersistenceHomeAudioSystem.loadHomeAudioSystemModel();
        HomeAudioSystem has = HomeAudioSystem.getInstance();

        refreshData();

        // reload songs when album selected
        Spinner spinner = (Spinner) findViewById(R.id.albumSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                refreshSongs();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    private void refreshData() {
        HomeAudioSystem has = HomeAudioSystem.getInstance();

        HomeAudioSystemController hasc = new HomeAudioSystemController();

        albums =  new HashMap<Integer, ca.mcgill.ecse321.group01.homeaudiosystem.model.Album>();
        Spinner spinner = (Spinner) findViewById(R.id.albumSpinner);
        ArrayAdapter<CharSequence> albumAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        albumAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        for (ca.mcgill.ecse321.group01.homeaudiosystem.model.Album album: hasc.getAllAlbumsFromLibrary()) {
            albums.put(albums.size(), album);
            albumAdapter.add(album.getTitle());
        }
        spinner.setAdapter(albumAdapter);

        refreshSongs();

    }

    private void refreshSongs() {
        ArrayList<String> songs = new ArrayList<String>();
        Spinner spinner = (Spinner) findViewById(R.id.albumSpinner);
        Album album = albums.get(spinner.getSelectedItemPosition());
        for (Song song : album.getSongs()) {
            String title = song.getTitle();
            int minutes = song.getDuration() / 60;
            int seconds = song.getDuration() % 60;
            String artists = song.getArtist(0).getName();
            for (int i = 1; i < song.getArtists().size(); ++i) {
                artists += ", " + song.getArtist(i).getName();
            }
            songs.add(minutes + ":" + String.format("%02d", seconds) + "          " + title + "     " + artists);
        }

        ListView songListView = (ListView) findViewById(R.id.songs);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songs);
        songListView.setAdapter(arrayAdapter);

    }

    public void addAlbumPage (View view) {
        Intent intent = new Intent(this, AddAlbum.class);
        startActivity(intent);
    }

    public void removeAlbum(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.albumSpinner);
        ca.mcgill.ecse321.group01.homeaudiosystem.model.Album album = albums.get(spinner.getSelectedItemPosition());

        // call the controller
        HomeAudioSystemController hasc = new HomeAudioSystemController();
        hasc.removePlaylist(album);
        albums.remove(album);

        refreshData();
    }

}
