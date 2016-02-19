package ca.mcgill.ecse321.group01.homeaudiosystem.persistence;

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

}
