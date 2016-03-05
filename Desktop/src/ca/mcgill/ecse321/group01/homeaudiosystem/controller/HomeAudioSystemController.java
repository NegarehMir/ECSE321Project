package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;

public class HomeAudioSystemController {

	public void createAlbum(String title, Artist artist, Genre genre, Date releaseDate, AlbumTracklist playlist) throws InvalidInputException {
		String error = "";
		if (title == null || title.trim().length() == 0)
			error += "Album title cannot be empty! ";
		if (artist.getName() == null || artist.getName().trim().length() == 0)
			error = error +"Album artist name cannot be empty ";
		
		error = error.trim();
		if (error.length() > 0) 
			throw new InvalidInputException(error);

		Album album = new Album(title, releaseDate, genre, artist, playlist);
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.addAlbum(album);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createPlaylist(Playlist playlist) throws InvalidInputException {
		if(playlist.getName() == null || playlist.getName().trim().length() == 0)
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
	
	public void modifyLocation(Location oldLocation, Location newLocation) throws InvalidInputException {
		if(newLocation == null)
			throw new InvalidInputException("Please select a location!");
		if(newLocation.getName() == null || newLocation.getName().trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.removeLocation(oldLocation);
		has.addLocation(newLocation);
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
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.addSongToPlaylist(song, playlist);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void assignPlaylistToLocation(Playlist playlist, Location location){
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.assignPlaylistToLocation(playlist, location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void assignSongToLocation(Song song, Location location) {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.assignSongToLocation(song, location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void assignAlbumToLocation(Album album, Location location) {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		has.assignAlbumToLocation(album, location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
}
