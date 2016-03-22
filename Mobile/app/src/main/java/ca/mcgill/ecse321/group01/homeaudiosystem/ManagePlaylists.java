package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ManagePlaylists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_playlists);
    }

    public void createPlaylistPage (View view) {
        Intent intent = new Intent(this, CreatePlaylist.class);
        startActivity(intent);
    }

    public void addSongPlaylistPage(View view) {
        Intent intent = new Intent(this, AddSongPlaylist.class);
        startActivity(intent);
    }

}
