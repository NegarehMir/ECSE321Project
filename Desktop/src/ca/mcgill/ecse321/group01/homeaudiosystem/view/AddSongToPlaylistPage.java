package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;

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
	Playlist playlist;
	// UI elements
	private JLabel errorMessage;
	private JLabel playlistInfoLabel;
	private JLabel songLabel;
	private JComboBox<String> songList;
	private JButton addSongToPlaylistButton;

	// data elements
	private String error = null;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;
	
	// Creates new form AddAlbumPage
	public AddSongToPlaylistPage(Playlist playlist) {
		this.playlist = playlist;
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		playlistInfoLabel = new JLabel();
		playlistInfoLabel.setText("Adding a song to album \""+playlist.getTitle()+"\"");
		
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
								.addComponent(playlistInfoLabel)
								.addComponent(songLabel)
								.addComponent(addSongToPlaylistButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(songList)))
				);
						
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(playlistInfoLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(songLabel)
								.addComponent(songList))
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
			
			HomeAudioSystemController hasController = new HomeAudioSystemController();
			LinkedList<Song> allSongsInLibrary = hasController.getAllSongsFromLibrary();
			
			for (Song song: allSongsInLibrary) {
				if(!playlist.getSongs().contains(song))
				{	
					songs.put(songs.size(), song);
					songList.addItem(song.getTitle()+" - "+song.getArtist(0).getName());
				}
			}
			selectedSong = -1;
			songList.setSelectedIndex(selectedSong);
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
					playlist);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
}
