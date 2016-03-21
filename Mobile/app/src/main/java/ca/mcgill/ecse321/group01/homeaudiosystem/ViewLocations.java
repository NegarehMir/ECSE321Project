package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.LocationSongPlaying;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;

public class ViewLocations extends AppCompatActivity {
    ArrayList<String> locations = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        PersistenceHomeAudioSystem.setFileName(getFilesDir().getAbsolutePath() + "/" + "homeAudioSystem.xml");
        PersistenceHomeAudioSystem.loadHomeAudioSystemModel();
        HomeAudioSystem has = HomeAudioSystem.getInstance();

        for (ca.mcgill.ecse321.group01.homeaudiosystem.model.Location location: has.getLocations())
        {
            String currentSongName = "";
            Song currentSong = LocationSongPlaying.getCurrentlyPlayingSong(location);
            if(currentSong != null)
                currentSongName = "Current Song Playing: " + currentSong.getTitle();
            String name = location.getName() + "        ";
            int volume = location.getVolume();
            String mute;
            if (location.getMute()){
                mute = "Mute";
            } else {
                mute = "";
            }
            locations.add(name + "Volume: " + volume + "     " + mute + "   " + currentSongName);
        }

        ListView songListView = (ListView) findViewById(R.id.locations);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                locations);
        songListView.setAdapter(arrayAdapter);

    }
}
