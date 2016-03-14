package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album.Genres;
import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceXStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class HomeAudioSystemController {
	
	
	public LinkedList<Song> getAllSongsFromLibrary() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		LinkedList<Song> allSongsInLibrary = new LinkedList<>();
		List<Playlist> allPlaylistsInLibrary = has.getPlaylists();
		
		for (Playlist playlist : allPlaylistsInLibrary) {
			if (playlist instanceof Album) {
				for (Song song : playlist.getSongs()) {
					allSongsInLibrary.add(song);
				}
			}
		}
		return allSongsInLibrary;
	}
	
	public LinkedList<Album> getAllAlbumsFromLibrary() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		LinkedList<Album> allAlbumsInLibrary = new LinkedList<>();
		List<Playlist> allPlaylistsInLibrary = has.getPlaylists();
		
		for (Playlist playlist : allPlaylistsInLibrary) {
			if (playlist instanceof Album) {
				allAlbumsInLibrary.add((Album) playlist);
			}
		}
		
		return allAlbumsInLibrary;
	}
	
	public List<Playlist> getAllPlaylistsFromLibrary() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		return has.getPlaylists();
	}
	
	
	public void createAlbum(String title, String artistName, Date releaseDate, String genreName, LinkedList<SongMetadata> songMetadata) throws InvalidInputException {
		String error = "";
		if (title == null || title.trim().length() == 0)
			error += "Album title cannot be empty! ";
		if (artistName == null || artistName.trim().length() == 0)
			error += "Album artist name cannot be empty! ";
		Genres genre = null;
		try {
			genre = Genres.valueOf(genreName.trim());
		} catch (IllegalArgumentException e) {
			error += "Invalid album genre! ";
		} catch (NullPointerException e) {
			error += "Empty album genre! ";
		}
		
		error = error.trim();
		if (error.length() > 0) 
			throw new InvalidInputException(error);

		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Artist artist = createArtist(artistName);
		Album album = new Album(title, has, releaseDate, artist);
		
		album.setGenre(genre);
		
		for (SongMetadata metadata : songMetadata) {
			String songTitle = metadata.getTitle();
			int songDuration = metadata.getDuration();
			
			Song song = new Song(songTitle, songDuration, album);
			song.addArtist(artist);
			album.addSong(song);
		}
		
		has.addPlaylist(album);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	private Artist createArtist(String artistName) {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		List<Artist> hasArtists = has.getArtists();
		
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
	
	public void createPlaylist(String playlistTitle, Song... songsToAdd) throws InvalidInputException {
		if(playlistTitle == null || playlistTitle.trim().length() == 0)
			throw new InvalidInputException("Playlist name cannot be empty!");
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Playlist playlist = new Playlist(playlistTitle, has, songsToAdd);
		
		has.addPlaylist(playlist);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createLocation(String locationName) throws InvalidInputException {
		if(locationName == null || locationName.trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Location location = new Location(locationName, has);
		
		has.addLocation(location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void createLocation(String locationName, int volume, boolean isMute) throws InvalidInputException {
		if(locationName == null || locationName.trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		Location location = new Location(locationName, has);
		
		setLocationVolume(location, volume);
		location.setMute(isMute);
		
		has.addLocation(location);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void modifyLocation(Location oldLocation, String newLocationName, int newLocationVolume, boolean newLocationMute) throws InvalidInputException {
		if(oldLocation == null)
			throw new InvalidInputException("Please select a location!");
		if(newLocationName == null || newLocationName.trim().length() == 0)
			throw new InvalidInputException("Location name cannot be empty!");
		
		oldLocation.delete();
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		
		Location newLocation = new Location(newLocationName, has);
		setLocationVolume(newLocation, newLocationVolume);
		newLocation.setMute(newLocationMute);
		
		has.addLocation(newLocation);
		PersistenceXStream.saveToXMLwithXStream(has);
	}
	
	public void setLocationVolume(Location location, int volume) throws InvalidInputException {
		if (volume > location.MaxVolume) {
			throw new InvalidInputException("New volume exceeds maximum value!");
		} else if (volume < location.MinVolume) {
			throw new InvalidInputException("New volume smaller than minimum value!");
		} else {
			location.setVolume(volume);
		}
		
		//TODO: save to XML here?
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
	
	  public boolean assignPlaylistToLocation(Playlist aPlaylist, Location aLocation)
	  {
		  //TODO: assign change currently playing song index depending on where playlist added
		  //LocationSongPlaying.setPlayingSongIndex(aLocation, 0);
		  boolean wasAdded = false;
		  
		  List<LocationMusicItem> locationMusicItems = aLocation.getLocationMusicItems();
		  List<Song> playlistSongs = aPlaylist.getSongs();
		  
		  if (locationMusicItems.equals(aPlaylist.getSongs())) 
			  return false;
		  
		  for(int i = locationMusicItems.size(); i>0; i--)
			  aLocation.removeLocationMusicItem(locationMusicItems.get(i-1));
		  
		  for(Song song: aPlaylist.getSongs())
			  aLocation.addLocationMusicItem(song);
		  
		  wasAdded = true;
		  return wasAdded;
	  }
	  
	  public boolean assignSongToLocation(Song aSong, Location aLocation)
	  {
		  //TODO: assign change currently playing song index depending on where song added
		  //LocationSongPlaying.setPlayingSongIndex(aLocation, 0);
		  boolean wasAdded = false;
		  
		  List<LocationMusicItem> locationMusicItems = aLocation.getLocationMusicItems();
		  
		  if (locationMusicItems.equals(aSong))
			  return false;
		  
		  for(int i = locationMusicItems.size(); i>0; i--)
			  aLocation.removeLocationMusicItem(locationMusicItems.get(i-1));
		  
		  aLocation.addLocationMusicItem(aSong);
		  
		  wasAdded = true;
		  return wasAdded;  
	  }
	
	public String getCurrentSongName(Location location) {
		Song songPlaying = LocationSongPlaying.getCurrentlyPlayingSong(location);

		  if (songPlaying == null)
			  return "-";
		  else
			  return songPlaying.getTitle()+" - " + songPlaying.getArtist(0).getName();
	 }
}
