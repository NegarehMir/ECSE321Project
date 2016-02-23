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

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.*;

public class MainActivity extends AppCompatActivity {

    private String error = null;
    private HashMap<Integer, Genre> genres;

    private ArrayList<String[]> songs = new ArrayList<String[]>();

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

        // prepopulate generes if there aren't any at all
        if (has.getGenres().size() <= 0) {
            String[] genreNames = {
                    "Alternative",
                    "Classical",
                    "Country",
                    "Electronic",
                    "Hip-Hop/Rap",
                    "Pop",
                    "Rock",
                    "Jazz",
            };
            for(String name: genreNames) {
                has.addGenre(new Genre(name));
            }
        }

        refreshData();
    }

    private void refreshData() {
        ArrayList<String[]> songs = new ArrayList<String[]>();

        HomeAudioSystem has = HomeAudioSystem.getInstance();

        TextView tv = (TextView) findViewById(R.id.newalbum_title);
        tv.setText("");

        tv = (TextView) findViewById(R.id.newalbum_artistname);
        tv.setText("");


        Spinner spinner = (Spinner) findViewById(R.id.newalbum_genrespinner);
        ArrayAdapter<CharSequence> genresAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        genresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genres = new HashMap<Integer, Genre>();
        for (Genre genre: has.getGenres()) {
            genresAdapter.add(genre.getName());
            genres.put(genres.size(), genre);
        }
        spinner.setAdapter(genresAdapter);

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
        String title = ((TextView) findViewById(R.id.newalbum_title)).getText().toString();
        String artistName = ((TextView)findViewById(R.id.newalbum_artistname)).getText().toString();
        Genre genre = genres.get(((Spinner) findViewById(R.id.newalbum_genrespinner)).getSelectedItemPosition());
        CharSequence date = ((TextView)findViewById(R.id.newalbum_date)).getText();

        if (artistName == null || artistName.trim().length() == 0) {
            error = error + "Album artist name cannot be empty ";
        }
        Artist artist = new Artist(artistName);

        Bundle dateBundle = getDateFromLabel(date);
        Date releaseDate = dateBundle(dateBundle);

        // Create the track list
        AlbumTracklist trackList = new AlbumTracklist("");
        for (int i = 0; i < songs.size(); i++) {
            String songTitle = songs.get(i)[0];
            Bundle timeBundle = getTimeFromLabel(songs.get(i)[1]);
            int duration = 60 * timeBundle.getInt("hour") + timeBundle.getInt("minute");
            trackList.addSong(new Song(songTitle, duration, artist));
        }

        // call the controller
        HomeAudioSystemController hasc = new HomeAudioSystemController();
        try {
            hasc.createAlbum(
                    title,
                    artist,
                    genre,
                    releaseDate,
                    trackList);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }

        refreshData();
    }

    public void addSong(View v) {
        String duration = ((TextView) findViewById(R.id.newsong_duration)).getText().toString();
        String title = ((TextView) findViewById(R.id.newsong_title)).getText().toString();
        songs.add(new String[] { title, duration });
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

//    private int getTime(CharSequence text) {
//        int duration = 0;
//        String comps[] = text.toString().split(":");
//        if (comps.length == 2) {
//             duration = Integer.parseInt(comps[0]) * 60 +Integer.parseInt(comps[1]);
//        }
//        return duration;
//    }

//    private int getTime(Bundle timeBundle) {
//        int duration = 0;
//        int unbundleMinute = inputTime.getInt("minute");
//        int unbundleSeconds = inputTime.getInt("seconds");
//            duration = unbundleMinute * 60 + unbundleSeconds;
//        return duration;
//    }

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

    public void setTime(int id, int m, int s) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", m, s));
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
    public Date dateBundle (Bundle inputDate) {
        int unbundleDay = inputDate.getInt("day");
        int unbundleMonth = inputDate.getInt("month");
        int unbundleYear = inputDate.getInt("year");

        Date date = new Date(unbundleDay, unbundleMonth, unbundleYear);
        return date;
    }

    public Time timeBundle (Bundle inputTime) {
        int unbundleHour = inputTime.getInt("hour");
        int unbundleMinute = inputTime.getInt("minute");
        Time time = new Time(unbundleHour, unbundleMinute, 0);
        return time;

    }

}
