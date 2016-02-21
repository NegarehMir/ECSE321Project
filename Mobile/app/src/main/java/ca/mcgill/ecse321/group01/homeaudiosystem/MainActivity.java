package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

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
        has = HomeAudioSystemManager.getInstance();

        refreshData();s
    }

    private void refreshData() {
        TextView tv = (TextView) findViewById(R.id.newalbum_title);
        tv.setText("");

        tv = (TextView) findViewById(R.id.newalbum_artistname);
        tv.setText("");

        tv = (TextView) findViewById(R.id.newalbum_genre);
        tv.setText("");

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
        String title = (TextView) findViewById(R.id.newalbum_title).toString();
        String artistName = (TextView) findViewById(R.id.newalbum_artistname).toString();
        String genre = (TextView) findViewById(R.id.newalbum_genre).toString();
        String date = (TextView) findViewById(R.id.newalbum_date).toString();
        if(date!= null && date != "") {
            String[] dates = date.split("-");
            day = parseInt(dates[0]);
            month = parseInt(dates[1]);
            year = parseInt(dates[2]);
            c.set(year, month, day);
            java.util.Date dateFromJavaUtilCalendar = c.getTime();
            long timeStampFromJavaUtilCalendar = dateFromJavaUtilCalendar.getTime();
            d = new Date(timeStampFromJavaUtilCalendar);
        }

        HomeAudioSystemController hc = new HomeAudioSystemController();
        try {
            hc.createAlbum(title, artistName, genre, d);
            clearErrorMessage();
        } catch (InvalidInputException e) {
            showErrorMessage(e);
        }
        refreshData();
    }
}
