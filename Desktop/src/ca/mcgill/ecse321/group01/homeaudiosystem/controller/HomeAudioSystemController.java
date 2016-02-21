package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.AlbumTracklist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Artist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;

public class HomeAudioSystemController {

	public void addAlbum(String title, Artist artist, Genre genre, Date releaseDate, AlbumTracklist playlist) throws InvalidInputException {
		if (title == null || title.trim().length() == 0) {
			throw new InvalidInputException("Title cannot be empty!");
		}
		Album album = new Album(title, releaseDate, genre, artist, playlist);
		HomeAudioSystem homeAudioSystem = HomeAudioSystem.getInstance();
		homeAudioSystem.addAlbum(album);
		PersistenceXStream.saveToXMLwithXStream(homeAudioSystem);
	}
}
