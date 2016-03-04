package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;

public class TestHomeAudioSystemController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename(
				"test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator
					+ "group01" + File.separator + "homeaudiosystem" + File.separator + "controller" + File.separator + "data.xml");
		
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
	}
	
	@After
	public void tearDown() throws Exception {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.delete();
	}
	
}
