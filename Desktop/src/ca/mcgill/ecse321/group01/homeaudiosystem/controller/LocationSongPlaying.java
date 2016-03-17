package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import java.util.HashMap;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.LocationMusicItem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class LocationSongPlaying {

	private static HashMap<Location, Integer> locationMusicItemCurrentlyPlayingIndexMap = new HashMap<Location, Integer>();
	private static HashMap<Location, Integer> withinLocationMusicItemCurrentlyPlayingIndexMap = new HashMap<Location, Integer>();
	
	public void addLocationSongPlaying(Location location, LocationMusicItem locationMusicItem)
	{
		if(locationMusicItem instanceof Song)
			locationMusicItemCurrentlyPlayingIndexMap.put(location, 0);
		else
			withinLocationMusicItemCurrentlyPlayingIndexMap.put(location, 0);
	}
	
	public static Song getCurrentlyPlayingSong(Location location) {
		if (locationMusicItemCurrentlyPlayingIndexMap != null && locationMusicItemCurrentlyPlayingIndexMap.size()>0) {
			int currentlyPlayingLocationMusicItemIndex = locationMusicItemCurrentlyPlayingIndexMap.get(location);
			LocationMusicItem locationMusicItemCurrentlyPlaying = location
					.getLocationMusicItem(currentlyPlayingLocationMusicItemIndex);

			if (locationMusicItemCurrentlyPlaying instanceof Song) {
				return (Song) locationMusicItemCurrentlyPlaying;

			} else if (locationMusicItemCurrentlyPlaying instanceof Playlist) {
				int currentlyPlayingWithinLocationMusicItemIndex = withinLocationMusicItemCurrentlyPlayingIndexMap
						.get(location);
				Song song = ((Playlist) locationMusicItemCurrentlyPlaying)
						.getSong(currentlyPlayingWithinLocationMusicItemIndex);
				return song;
			}
		}
		return null;
	}
}
