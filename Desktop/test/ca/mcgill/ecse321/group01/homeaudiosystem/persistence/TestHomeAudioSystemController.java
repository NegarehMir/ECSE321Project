package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album.Genres;
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
	
	@Test
	public void testCreatedPlaylist(){
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "Chill";
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			Playlist playlist = new Playlist(title, has);
			hasController.createPlaylist(playlist);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultPlaylist(title, has);
		
		HomeAudioSystem has2 = (HomeAudioSystem) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultPlaylist(title, has2);
	}
	
	@Test
	public void testCreatedPlaylistNull() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = null;
		String error = null;
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			Playlist playlist = new Playlist(title, has);
			hasController.createPlaylist(playlist);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Playlist name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedPlaylistEmpty() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "";
		String error = null;
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			Playlist playlist = new Playlist(title, has);
			hasController.createPlaylist(playlist);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Playlist name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testAddSongToPlaylist() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();

		String playlistTitle = "Workout";
		Playlist playlist = new Playlist(playlistTitle, has);
		has.addPlaylist(playlist);
		assertEquals(1, has.getPlaylists().size());
		
		Artist artist = new Artist("Beyonce", has);
		has.addArtist(artist);
		assertEquals(1, has.getArtists().size());
		
		Date releaseDate = Date.valueOf("2011-06-24");
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		Album album = new Album("4", has, releaseDate, artist);
		
		Song song = new Song("Countdown", 213, album, artist);
		album.addSong(song);
		assertEquals(1, album.getSongs().size());
		
		playlist.addSong(song);
		assertEquals(1, playlist.getSongs().size());
	}
	
	
	private void checkResultPlaylist(String title, HomeAudioSystem has2) {
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(title, has2.getPlaylist(0).getTitle());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getLocations().size());
	}
	
}
