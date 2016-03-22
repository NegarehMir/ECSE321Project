package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Library extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    public void albumPage (View view) {
        Intent intent = new Intent(this, AlbumPage.class);
        startActivity(intent);
    }

    public void playlistPage (View view) {
        Intent intent = new Intent(this, Playlist.class);
        startActivity(intent);
    }

    public void managePlaylistsPage(View view) {
        Intent intent = new Intent(this, ManagePlaylists.class);
        startActivity(intent);
    }

    public void addAlbumPage(View view) {
        Intent intent = new Intent(this, AddAlbum.class);
        startActivity(intent);
    }
}
