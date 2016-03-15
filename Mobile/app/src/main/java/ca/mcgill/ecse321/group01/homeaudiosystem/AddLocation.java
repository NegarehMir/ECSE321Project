package ca.mcgill.ecse321.group01.homeaudiosystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;

public class AddLocation extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
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

        TextView tv = (TextView) findViewById(R.id.newlocation_title);
        tv.setText("");

        SeekBar volumeSeekbar = (SeekBar)findViewById(R.id.volume_bar);
        volumeSeekbar.setProgress(50);

        Switch muteSwitch = (Switch) findViewById(R.id.mute_switch);
        muteSwitch.setChecked(false);

    }

    public void addLocation(View v) {
        String locationName = ((TextView) findViewById(R.id.newlocation_title)).getText().toString();
        SeekBar volumeSeekbar = (SeekBar)findViewById(R.id.volume_bar);
        Switch muteSwitch = (Switch) findViewById(R.id.mute_switch);
        int volume = volumeSeekbar.getProgress();
        boolean isMute = muteSwitch.isChecked();

        HomeAudioSystemController hasc = new HomeAudioSystemController();
        try {
            hasc.createLocation(locationName, volume, isMute);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        refreshData();
    }

}
