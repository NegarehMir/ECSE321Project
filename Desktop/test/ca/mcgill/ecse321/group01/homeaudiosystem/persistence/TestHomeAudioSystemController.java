package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.SongMetadata;
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
	public void testCreatedAlbum(){
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "The Dark Side of the Moon";
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String artistName = "Pink Floyd";
		
		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));
		
		String genreName = "Alternative";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
			
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultAlbum(title, artistName, releaseDate, genreName, has);
		
		HomeAudioSystem has2 = (HomeAudioSystem) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultAlbum(title, artistName, releaseDate, genreName, has2);
	}
	
	@Test
	public void testCreatedAlbumNull() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = null;
		String error = null;
		String artistName = null;
		Date releaseDate = null;
		String genreName = null;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Album title cannot be empty! Album artist name cannot be empty! Empty album genre!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumTitleEmpty() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "";
		String error = null;
		String artistName = "Pink Floyd";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = "Alternative";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Album title cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumTitleSpaces() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = " ";
		String error = null;
		String artistName = "Pink Floyd";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = "Alternative";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Album title cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumArtistEmpty() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "The Dark Side of the Moon";
		String error = null;
		String artistName = "";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = "Alternative";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Album artist name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumArtistSpaces() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "The Dark Side of the Moon";
		String error = null;
		String artistName = " ";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = "Alternative";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Album artist name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumGenreEmpty() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "The Dark Side of the Moon";
		String error = null;
		String artistName = "Pink FLoyd";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = null;

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Empty album genre!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedAlbumInvalidGenre() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "The Dark Side of the Moon";
		String error = null;
		String artistName = "Pink FLoyd";
		
		Calendar c = Calendar.getInstance();
		c.set (2016,Calendar.OCTOBER, 16, 9, 00, 0) ;
		Date releaseDate = new Date(c.getTimeInMillis());

		String songTitle = "Time";
		int songDuration = 242;
		LinkedList<SongMetadata> songInfo = new LinkedList<>();
		songInfo.add(new SongMetadata(songTitle, songDuration));

		String genreName = "hjkhl";

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createAlbum(title, artistName, releaseDate, genreName, songInfo);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
			
		//check error
		assertEquals("Invalid album genre!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
		
	@Test
	public void testCreatedPlaylist(){
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getPlaylists().size());
		
		String title = "Chill";
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createPlaylist(title);
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
			hasController.createPlaylist(title);
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
			hasController.createPlaylist(title);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Playlist name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getPlaylists().size());
	}
	
	@Test
	public void testCreatedLocation(){
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getLocations().size());
		
		String locationName = "Living Room";
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createLocation(locationName);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultLocation(locationName, has);
		
		HomeAudioSystem has2 = (HomeAudioSystem) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultLocation(locationName, has2);
	}
	
	@Test
	public void testCreatedLocationNull() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getLocations().size());
		
		String locationName = null;
		String error = null;

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createLocation(locationName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Location name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getLocations().size());
	}

	@Test
	public void testCreatedLocationEmpty() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getLocations().size());
		
		String locationName = "";
		String error = null;

		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.createLocation(locationName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Location name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, has.getLocations().size());
	}
	
	
	@Test
	public void testAssignLocationMusicItemToLocation() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		assertEquals(0, has.getLocations().size());
		
		Location location = new Location("Dining Room", has);
		Artist artist = new Artist("Arcade Fire", has);
		Album album = new Album("Reflektor", has, Date.valueOf("2013-10-28"), artist);
		Song song = new Song("Afterlife", 353, album);
		
		Playlist playlist = new Playlist("Birthday", has, song);
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		
		try {
			hasController.assignLocationMusicItemToLocation(playlist, location);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals(1, location.getLocationMusicItems().size());
	}
	
	
	@Test
	public void testRemoveSongFromPlaylist() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		
		Artist artist = new Artist("Arcade Fire", has);
		Album album = new Album("Reflektor", has, Date.valueOf("2013-10-28"), artist);
		Song song = new Song("Afterlife", 353, album);
		Playlist playlist = new Playlist("Birthday", has, song);
		
		HomeAudioSystemController hasController = new HomeAudioSystemController();
		try {
			hasController.removeSongFromPlaylist(playlist, 0);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals(0, playlist.getSongs().size());
	}

	
	@Test
	public void testMoveSongUpInPlaylist() {
		
	}
	
	@Test
	public void testMoveSonDownInPlaylist() {

	}
	
	private void checkResultPlaylist(String title, HomeAudioSystem has2) {
		assertEquals(1, has2.getPlaylists().size());
		assertEquals(title, has2.getPlaylist(0).getTitle());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getLocations().size());
	}
	
	private void checkResultAlbum(String title, String artist, Date releaseDate, String genre, HomeAudioSystem has2) {
		assertEquals(1, has2.getPlaylists().size());
		assertTrue("Playlist expected to be an Album", has2.getPlaylist(0) instanceof Album);
		Album album = (Album) has2.getPlaylist(0);
		assertEquals(title, album.getTitle());
		assertEquals(releaseDate, album.getReleaseDate());
		assertEquals(genre, album.getGenre());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getLocations().size());
	}
	
	private void checkResultLocation(String title, HomeAudioSystem has2) {
		assertEquals(1, has2.getLocations().size());
		assertEquals(title, has2.getLocations().get(0).getName());
		assertEquals(0, has2.getArtists().size());
		assertEquals(0, has2.getPlaylists().size());
	}
}
