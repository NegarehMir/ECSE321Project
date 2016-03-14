package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

import java.util.Iterator;

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
			Iterator<Artist> artistIt = has2.getArtists().iterator();
			for(int i =0; i<numArtist; i++) {
				has.addArtist(artistIt.next());
			}
			
			int numLocation = has2.getLocations().size();
			Iterator<Location> locationIt = has2.getLocations().iterator();
			for(int i =0; i<numLocation; i++) {
				Location nextLocation = locationIt.next();
				has.addLocation(nextLocation);
			}
			
			int numPlaylist = has2.getPlaylists().size();
			Iterator<Playlist> playlistIt = has2.getPlaylists().iterator();
			for(int i =0; i<numPlaylist; i++) {
				has.addPlaylist(playlistIt.next());
			}			
		}
	}
	
	public static void setFileName(String name) {
		fileName = name;
	}

}
