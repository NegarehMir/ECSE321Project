/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;

import java.util.*;

/**
 * use PhpHomeAudioSystem.ump;
 */
// line 6 "../../../../../../../Umple/HomeAudioSystem.ump"
// line 96 "../../../../../../../Umple/HomeAudioSystem.ump"
public class HomeAudioSystem {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static HomeAudioSystem theInstance = null;

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// HomeAudioSystem Associations
	private List<Location> locations;
	private List<Artist> artists;
	private List<Playlist> playlists;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	private HomeAudioSystem() {
		locations = new ArrayList<Location>();
		artists = new ArrayList<Artist>();
		playlists = new ArrayList<Playlist>();
	}

	public static HomeAudioSystem getInstance() {
		if (theInstance == null) {
			theInstance = new HomeAudioSystem();
		}
		return theInstance;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public Location getLocation(int index) {
		Location aLocation = locations.get(index);
		return aLocation;
	}

	public List<Location> getLocations() {
		List<Location> newLocations = Collections.unmodifiableList(locations);
		return newLocations;
	}

	public int numberOfLocations() {
		int number = locations.size();
		return number;
	}

	public boolean hasLocations() {
		boolean has = locations.size() > 0;
		return has;
	}

	public int indexOfLocation(Location aLocation) {
		int index = locations.indexOf(aLocation);
		return index;
	}

	public Artist getArtist(int index) {
		Artist aArtist = artists.get(index);
		return aArtist;
	}

	public List<Artist> getArtists() {
		List<Artist> newArtists = Collections.unmodifiableList(artists);
		return newArtists;
	}

	public int numberOfArtists() {
		int number = artists.size();
		return number;
	}

	public boolean hasArtists() {
		boolean has = artists.size() > 0;
		return has;
	}

	public int indexOfArtist(Artist aArtist) {
		int index = artists.indexOf(aArtist);
		return index;
	}

	public Playlist getPlaylist(int index) {
		Playlist aPlaylist = playlists.get(index);
		return aPlaylist;
	}

	public List<Playlist> getPlaylists() {
		List<Playlist> newPlaylists = Collections.unmodifiableList(playlists);
		return newPlaylists;
	}

	public int numberOfPlaylists() {
		int number = playlists.size();
		return number;
	}

	public boolean hasPlaylists() {
		boolean has = playlists.size() > 0;
		return has;
	}

	public int indexOfPlaylist(Playlist aPlaylist) {
		int index = playlists.indexOf(aPlaylist);
		return index;
	}

	public static int minimumNumberOfLocations() {
		return 0;
	}

	public Location addLocation(String aName) {
		return new Location(aName, this);
	}

	public boolean addLocation(Location aLocation) {
		boolean wasAdded = false;
		if (locations.contains(aLocation)) {
			return false;
		}
		HomeAudioSystem existingHomeAudioSystem = aLocation.getHomeAudioSystem();
		boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
		if (isNewHomeAudioSystem) {
			aLocation.setHomeAudioSystem(this);
		} else {
			locations.add(aLocation);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeLocation(Location aLocation) {
		boolean wasRemoved = false;
		// Unable to remove aLocation, as it must always have a homeAudioSystem
		if (!this.equals(aLocation.getHomeAudioSystem())) {
			locations.remove(aLocation);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	public boolean addLocationAt(Location aLocation, int index) {
		boolean wasAdded = false;
		if (addLocation(aLocation)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLocations()) {
				index = numberOfLocations() - 1;
			}
			locations.remove(aLocation);
			locations.add(index, aLocation);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveLocationAt(Location aLocation, int index) {
		boolean wasAdded = false;
		if (locations.contains(aLocation)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLocations()) {
				index = numberOfLocations() - 1;
			}
			locations.remove(aLocation);
			locations.add(index, aLocation);
			wasAdded = true;
		} else {
			wasAdded = addLocationAt(aLocation, index);
		}
		return wasAdded;
	}

	public static int minimumNumberOfArtists() {
		return 0;
	}

	public Artist addArtist(String aName) {
		return new Artist(aName, this);
	}

	public boolean addArtist(Artist aArtist) {
		boolean wasAdded = false;
		if (artists.contains(aArtist)) {
			return false;
		}
		HomeAudioSystem existingHomeAudioSystem = aArtist.getHomeAudioSystem();
		boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
		if (isNewHomeAudioSystem) {
			aArtist.setHomeAudioSystem(this);
		} else {
			artists.add(aArtist);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeArtist(Artist aArtist) {
		boolean wasRemoved = false;
		// Unable to remove aArtist, as it must always have a homeAudioSystem
		if (!this.equals(aArtist.getHomeAudioSystem())) {
			artists.remove(aArtist);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	public boolean addArtistAt(Artist aArtist, int index) {
		boolean wasAdded = false;
		if (addArtist(aArtist)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfArtists()) {
				index = numberOfArtists() - 1;
			}
			artists.remove(aArtist);
			artists.add(index, aArtist);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveArtistAt(Artist aArtist, int index) {
		boolean wasAdded = false;
		if (artists.contains(aArtist)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfArtists()) {
				index = numberOfArtists() - 1;
			}
			artists.remove(aArtist);
			artists.add(index, aArtist);
			wasAdded = true;
		} else {
			wasAdded = addArtistAt(aArtist, index);
		}
		return wasAdded;
	}

	public static int minimumNumberOfPlaylists() {
		return 0;
	}

	public Playlist addPlaylist(String aTitle, Song... allSongs) {
		return new Playlist(aTitle, this, allSongs);
	}

	public boolean addPlaylist(Playlist aPlaylist) {
		boolean wasAdded = false;
		if (playlists.contains(aPlaylist)) {
			return false;
		}
		HomeAudioSystem existingHomeAudioSystem = aPlaylist.getHomeAudioSystem();
		boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
		if (isNewHomeAudioSystem) {
			aPlaylist.setHomeAudioSystem(this);
		} else {
			playlists.add(aPlaylist);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removePlaylist(Playlist aPlaylist) {
		boolean wasRemoved = false;
		// Unable to remove aPlaylist, as it must always have a homeAudioSystem
		if (!this.equals(aPlaylist.getHomeAudioSystem())) {
			playlists.remove(aPlaylist);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	public boolean addPlaylistAt(Playlist aPlaylist, int index) {
		boolean wasAdded = false;
		if (addPlaylist(aPlaylist)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPlaylists()) {
				index = numberOfPlaylists() - 1;
			}
			playlists.remove(aPlaylist);
			playlists.add(index, aPlaylist);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMovePlaylistAt(Playlist aPlaylist, int index) {
		boolean wasAdded = false;
		if (playlists.contains(aPlaylist)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPlaylists()) {
				index = numberOfPlaylists() - 1;
			}
			playlists.remove(aPlaylist);
			playlists.add(index, aPlaylist);
			wasAdded = true;
		} else {
			wasAdded = addPlaylistAt(aPlaylist, index);
		}
		return wasAdded;
	}

	public void delete() {
		while (locations.size() > 0) {
			Location aLocation = locations.get(locations.size() - 1);
			aLocation.delete();
			locations.remove(aLocation);
		}

		while (artists.size() > 0) {
			Artist aArtist = artists.get(artists.size() - 1);
			aArtist.delete();
			artists.remove(aArtist);
		}

		while (playlists.size() > 0) {
			Playlist aPlaylist = playlists.get(playlists.size() - 1);
			aPlaylist.delete();
			playlists.remove(aPlaylist);
		}
	}
	
	public void moveSongUpInPlaylist(Playlist playlist, int position) {
		Song song = playlist.getSong(position);
		playlist.removeSong(song);
		playlist.addSongAt(song, position-1);
	}
	
	public void moveSongDownInPlaylist(Playlist playlist, int position) {
		Song song = playlist.getSong(position);
		playlist.removeSong(song);
		playlist.addSongAt(song, position+1);
	}

}