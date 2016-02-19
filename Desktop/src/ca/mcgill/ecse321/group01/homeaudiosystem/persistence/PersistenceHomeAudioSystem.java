package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.AlbumTracklist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class PersistenceHomeAudioSystem {
	
	private static String fileName = "homeaudiosystem.xml";
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename(fileName);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("albumTracklist", AlbumTracklist.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("genre", Genre.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
		PersistenceXStream.setAlias("song", Song.class);
	}
	
	public static void loadHomeAudioSystemModel() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		PersistenceHomeAudioSystem.initializeXStream();
		HomeAudioSystem has2 = (HomeAudioSystem) PersistenceXStream.loadFromXMLwithXStream();
		
		if (has2 != null) {
			Iterator<Album> albumIt = has2.getAlbums().iterator();
			while (albumIt.hasNext()) {
				has.addAlbum(albumIt.next());
			}
			
			Iterator<Artist> artistIt = has2.getArtists().iterator();
			while (artistIt.hasNext()) {
				has.addArtist(artistIt.next());
			}
			
			Iterator<Genre> genreIt = has2.getGenres().iterator();
			while (genreIt.hasNext()) {
				has.addGenre(genreIt.next());
			}
			
			Iterator<Location> locationIt = has2.getLocations().iterator();
			while (locationIt.hasNext()) {
				has.addLocation(locationIt.next());
			}
			
			Iterator<Playlist> playlistIt = has2.getPlaylists().iterator();
			while (playlistIt.hasNext()) {
				has.addPlaylist(playlistIt.next());
			}			
		}
	}
	
	public static void setFileName(String name) {
		fileName = name;
	}

}
