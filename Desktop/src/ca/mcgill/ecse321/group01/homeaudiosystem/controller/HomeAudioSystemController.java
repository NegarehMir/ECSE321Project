package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;

public class HomeAudioSystemController {

	public void createAlbum(String title, Artist artist, Date releaseDate) throws InvalidInputException {
		String error = "";
		if (title == null || title.trim().length() == 0)
			error += "Album title cannot be empty! ";
		if (artist.getName() == null || artist.getName().trim().length() == 0)
			error = error +"Album artist name cannot be empty ";
		
		error = error.trim();
		if (error.length() > 0) 
			throw new InvalidInputException(error);

		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Album album = new Album(title, has, releaseDate, artist);
		has.addPlaylist(album);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createPlaylist(Playlist playlist) throws InvalidInputException {
		if(playlist.getTitle() == null || playlist.getTitle().trim().length() == 0)
			throw new InvalidInputException("Playlist name cannot be empty!");
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.addPlaylist(playlist);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createLocation(Location location) throws InvalidInputException {
		if(location.getName() == null || location.getName().trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.addLocation(location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void addSongToPlaylist(Song song, Playlist playlist) throws InvalidInputException {
		String error = "";
		if(song == null)
			error += "Please choose a song! ";
		if(playlist == null)
			error += "Please choose a playlist!";
		error = error.trim();
		
		if (error.length() > 0) 
			throw new InvalidInputException(error);

		playlist.addSong(song);
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		PersistenceXStream.saveToXMLwithXStream(has);
	}
}
