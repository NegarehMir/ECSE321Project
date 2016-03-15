package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import java.util.Iterator;
import java.util.List;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;

public class PersistenceHomeAudioSystem {
	
	private static String fileName = "homeaudiosystem.xml";
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename(fileName);
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
	}
	
	public static void loadHomeAudioSystemModel() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		PersistenceHomeAudioSystem.initializeXStream();
		HomeAudioSystem has2 = (HomeAudioSystem) PersistenceXStream.loadFromXMLwithXStream();
		
		if (has2 != null) {
			int numArtist = has2.getArtists().size();
			List<Artist> artists = has2.getArtists();
			for(int i =0; i<numArtist; i++) {
				Artist artist = artists.get(0);
				has.addArtist(artist);
			}
			
			int numLocation = has2.getLocations().size();
			List<Location> locations = has2.getLocations();
			for(int i =0; i<numLocation; i++) {
				Location location = locations.get(0);
				has.addLocation(location);
			}
			
			int numPlaylist = has2.getPlaylists().size();
			List<Playlist> playlists = has2.getPlaylists();
			for(int i =0; i<numPlaylist; i++) {
				Playlist playlist = playlists.get(0);
				has.addPlaylist(playlist);
			}			
		}
	}
	
	public static void setFileName(String name) {
		fileName = name;
	}

}
