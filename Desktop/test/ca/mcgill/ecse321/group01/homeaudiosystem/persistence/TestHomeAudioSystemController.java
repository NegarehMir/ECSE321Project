package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.AlbumTracklist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
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
		PersistenceXStream.setAlias("albumTracklist", AlbumTracklist.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("genre", Genre.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
		PersistenceXStream.setAlias("song", Song.class);
	}
	
	@After
	public void tearDown() throws Exception {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.delete();
	}
	
	@Test
	public void testCreatePlaylist() throws Exception {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "Chill";
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			Playlist playlist = new Playlist(title);
			hasController.createPlaylist(playlist);
		} catch (InvalidInputException e) {
			fail();
		}
		
		
		
		
	}
	
	
	private void checkResultPlaylist(String title, HomeAudioSystem has2) {
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(title, has2.getPlaylist(0).getName());
		assertEquals(0, has2.getAlbums().size());
		assertEquals(0, has2.getArtists().size());
	}
	
}
