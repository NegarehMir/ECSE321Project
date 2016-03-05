package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;

public class AddSongToPlaylistPage extends JFrame{
	private static final long serialVersionUID = -4246871321337326949L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel songLabel;
	private JComboBox<String> songList;
	private JLabel playlistLabel;
	private JComboBox<String> playlistList;
	private JButton addSongToPlaylistButton;
	//title artist duration
	// data elements
	private String error = null;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;
	private Integer selectedPlaylist = -1;
	private HashMap<Integer, Playlist> playlists;
	
	// Creates new form AddAlbumPage
	public AddSongToPlaylistPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Add Song to Playlist");
		
		//elements for songs
		songLabel = new JLabel();
		songLabel.setText("Select Song:");
		songList = new JComboBox<String>(new String[0]);
		songList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSong = cb.getSelectedIndex();
			}
		});
		
		//elements for songs
		playlistLabel = new JLabel();
		playlistLabel.setText("Select Playlist:");
		playlistList = new JComboBox<String>(new String[0]);
		playlistList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
			}
		});
		
		//add song button
		addSongToPlaylistButton = new JButton();
		addSongToPlaylistButton.setText("Add Song");
		addSongToPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongToPlaylistButtonActionPerformed(evt);
			}
		});
		
		// layout
		//columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(songLabel)
								.addComponent(playlistLabel)
								.addComponent(addSongToPlaylistButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(songList)
								.addComponent(playlistList)))
				);
						
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(songLabel)
								.addComponent(songList))
						.addGroup(layout.createParallelGroup()
								.addComponent(playlistLabel)
								.addComponent(playlistList))
						.addComponent(addSongToPlaylistButton))
				);
		pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			// song list
			songs =  new HashMap<Integer, Song>();
			songList.removeAllItems();
			for (Song song: has.getSongs()) {
				songs.put(songs.size(), song);
				songList.addItem(song.getTitle()+" - "+song.getArtist().getName());
			}
			selectedSong = -1;
			songList.setSelectedIndex(selectedSong);
		
			// playlist list
			playlists =  new HashMap<Integer, Playlist>();
			playlistList.removeAllItems();
			for (Playlist playlist: has.getPlaylists()) {
				playlists.put(playlists.size(), playlist);
				playlistList.addItem(playlist.getName());
			}
			selectedPlaylist = -1;
			playlistList.setSelectedIndex(selectedPlaylist);
		}
		
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	private void addSongToPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.addSongToPlaylist(
					songs.get(selectedSong),
					playlists.get(selectedPlaylist));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
}
