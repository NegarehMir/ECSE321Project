package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.*;

public class MainActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        PersistenceHomeAudioSystem.setFileName(getFilesDir().getAbsolutePath() + "/" + "homeAudioSystem.xml");
        PersistenceHomeAudioSystem.loadHomeAudioSystemModel();
        HomeAudioSystem has = HomeAudioSystem.getInstance();

        refreshData();
    }

    private void refreshData() {
        HomeAudioSystem has = HomeAudioSystem.getInstance();

        TextView tv = (TextView) findViewById(R.id.newalbum_title);
        tv.setText("");

        tv = (TextView) findViewById(R.id.newalbum_artistname);
        tv.setText("");

        ArrayList<Genre> genres = new ArrayList<Genre>();
        for (Genre genre: has.getGenres()) {
            genres.add(genre);
        }
        Spinner spinner = (Spinner) findViewById(R.id.newalbum_genre);
        ArrayAdapter<Genre> genreAdapter = new
                ArrayAdapter<Genre>(this, android.R.layout.simple_spinner_item, genres);
        spinner.setAdapter(genreAdapter);

        tv = (TextView) findViewById(R.id.newalbum_date);
        tv.setText("01-01-2016");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAlbum(View v) {
        String title = findViewById(R.id.newalbum_title).toString();
        String artistName = findViewById(R.id.newalbum_artistname).toString();
        String genre = findViewById(R.id.newalbum_genre).toString();
        String date = findViewById(R.id.newalbum_date).toString();

        if (artistName == null || artistName.trim().length() == 0) {
            error = error + "Album artist name cannot be empty ";
        }
        Artist artist = new Artist(artistName);

        // create the track list
        AlbumTracklist trackList = new AlbumTracklist("");

        // call the controller
        HomeAudioSystemController hasc = new HomeAudioSystemController();
//        try {
//            hasc.createAlbum(
//                    title,
//                    artist,
//                    genres.get(selectedGenre),
//                    (java.sql.Date) releaseDatePicker.getModel().getValue(),
//                    trackList);
//        } catch (InvalidInputException e) {
//            error = e.getMessage();
//        }

        refreshData();
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }
        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);
        return rtn;
    }

    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;
        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }
        rtn.putInt("day", day);
        rtn.putInt("month", month - 1);
        rtn.putInt("year", year);
        return rtn;
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }

}
