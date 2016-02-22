package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;

public class HomeAudioSystemController {

	public void createAlbum(String title, String artistName, Genre genre, Date releaseDate, AlbumTracklist playlist) throws InvalidInputException {
		if (title == null || title.trim().length() == 0) {
			throw new InvalidInputException("Title cannot be empty!");
		}
		Artist artist = new Artist(artistName);
		Album album = new Album(title, releaseDate, genre, artist, playlist);
		HomeAudioSystem homeAudioSystem = HomeAudioSystem.getInstance();
		homeAudioSystem.addAlbum(album);
		PersistenceXStream.saveToXMLwithXStream(homeAudioSystem);
	}
}
