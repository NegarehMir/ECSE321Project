package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

import java.util.LinkedList;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class SongController {

	public LinkedList<Song> getAllSongsFromLibrary() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
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

}
