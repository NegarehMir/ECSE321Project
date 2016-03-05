package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album.Genres;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;
import java.util.LinkedList;

public class HomeAudioSystemController {
	
	public LinkedList<Song> getAllSongsFromLibrary(HomeAudioSystem has) {
		LinkedList<Song> allSongsInLibrary = new LinkedList<>();
		LinkedList<Playlist> allPlaylistsInLibrary = (LinkedList<Playlist>) has.getPlaylists();
		
		for (Playlist playlist : allPlaylistsInLibrary) {
			if (playlist instanceof Album) {
				for (Song song : playlist.getSongs()) {
					allSongsInLibrary.add(song);
				}
			}
		}
		return allSongsInLibrary;
	}

	public void createAlbum(String title, String artistName, Date releaseDate, String genreName, LinkedList<SongMetadata> songMetadata) throws InvalidInputException {
		String error = "";
		if (title == null || title.trim().length() == 0)
			error += "Album title cannot be empty! ";
		if (artistName == null || artistName.trim().length() == 0)
			error = error +"Album artist name cannot be empty ";
		if (Genres.valueOf(genreName) == null || artistName.trim().length() == 0)
			error += "Invalid or empty album genre!";
		
		error = error.trim();
		if (error.length() > 0) 
			throw new InvalidInputException(error);

		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Artist artist = createArtist(artistName);
		Album album = new Album(title, has, releaseDate, artist);
		
		Genres genre = Genres.valueOf(genreName);
		album.setGenre(genre);
		
		for (SongMetadata metadata : songMetadata) {
			String songTitle = metadata.getTitle();
			int songDuration = metadata.getDuration();
			
			Song song = new Song(songTitle, songDuration, album, artist);
			album.addSong(song);
		}
		
		has.addPlaylist(album);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	private Artist createArtist(String artistName) {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		LinkedList<Artist> hasArtists = (LinkedList<Artist>) has.getArtists();
		
		for (Artist hasArtist : hasArtists) {
			String hasArtistName = hasArtist.getName();
			if (hasArtistName.equals(artistName)) {
				return hasArtist;
			}
		}
		
		Artist artist = new Artist(artistName, has);
		has.addArtist(artist);
		PersistenceXStream.saveToXMLwithXStream(has);
		
		return artist;
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
