package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    public void addLocationPage (View view) {
        Intent intent = new Intent(this, AddLocation.class);
        startActivity(intent);
    }

    public void viewLocationsPage (View view) {
        Intent intent = new Intent(this, ViewLocations.class);
        startActivity(intent);
    }
}
