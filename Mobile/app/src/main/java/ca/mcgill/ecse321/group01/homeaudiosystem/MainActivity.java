package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void locationPage (View view) {
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }

    public void libraryPage (View view) {
        Intent intent = new Intent(this, Library.class);
        startActivity(intent);
    }

}

