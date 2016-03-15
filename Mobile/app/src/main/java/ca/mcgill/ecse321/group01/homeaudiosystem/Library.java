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

    public void addAlbumPage (View view) {
        Intent intent = new Intent(this, AddAlbum.class);
        startActivity(intent);
    }

}
