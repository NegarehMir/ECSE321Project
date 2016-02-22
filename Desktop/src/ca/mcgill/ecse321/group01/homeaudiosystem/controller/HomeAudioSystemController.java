package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;

public class HomeAudioSystemController {

	public void createAlbum(String title, Artist artist, Genre genre, Date releaseDate, AlbumTracklist playlist) throws InvalidInputException {
		String error = "";
		if (title == null || title.trim().length() == 0)
			error = error +"Album title cannot be empty! ";
		if (artist.getName() == null || artist.getName().trim().length() == 0)
			error = error +"Album artist name cannot be empty ";
		
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		Album album = new Album(title, releaseDate, genre, artist, playlist);
		HomeAudioSystem homeAudioSystem = HomeAudioSystem.getInstance();
		homeAudioSystem.addAlbum(album);
		PersistenceXStream.saveToXMLwithXStream(homeAudioSystem);
	}
}
