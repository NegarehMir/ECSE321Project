package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;

public class CreatePlaylistPage extends JFrame {
	private static final long serialVersionUID = -631603032690521965L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel playlistNameLabel;
	private JTextField playlistNameTextField;
	private JLabel songLabel;
	private JComboBox<String> songList;
	private JButton createPlaylistButton;
	private JButton addSongButton;
	private	JTable songsTable;
	private	JScrollPane songScrollPane;
	
	// data elements
	private String error = null;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;
	
	// Creates new form AddAlbumPage
	public CreatePlaylistPage() {
		initComponents();
		refreshData();
	}
		
	private void initComponents() {
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for playlist name
		playlistNameTextField = new JTextField();
		playlistNameLabel = new JLabel();
		
		//elements for songs
		songList = new JComboBox<String> (new String[0]);
		songList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSong = cb.getSelectedIndex();
			}
		});
		songLabel = new JLabel();
		songLabel.setText("Select Songs:");
		
		// elements for songs table
		songsTable = new JTable(new DefaultTableModel(new Object[] { "Index", "Song Title", "Artist" }, 0));
		songScrollPane = new JScrollPane(songsTable);
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Create Playlist");
		
		playlistNameLabel.setText("Playlist Name:");
		
		addSongButton = new JButton();
		createPlaylistButton = new JButton();
		
		addSongButton.setText("Add Song");
		addSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongButtonActionPerformed(evt);
			}
		});
		
		createPlaylistButton.setText("Create Playlist");
		createPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createPlaylistButtonActionPerformed(evt);
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
								.addComponent(playlistNameLabel)
								.addComponent(songLabel)
								.addComponent(addSongButton)
								.addComponent(createPlaylistButton)))
						.addGroup(layout.createParallelGroup()
								.addComponent(playlistNameTextField)
								.addComponent(songList))
						.addGroup(layout.createParallelGroup()
								.addComponent(songScrollPane, 200, 200, 400))
				);
		
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(playlistNameLabel)
								.addComponent(playlistNameTextField))
						.addGroup(layout.createSequentialGroup()
								.addComponent(songLabel)
								.addComponent(songList))
						.addComponent(songScrollPane, 200, 200, 400)
						.addGroup(layout.createSequentialGroup()
								.addComponent(addSongButton)
								.addComponent(createPlaylistButton)))
				);
		pack();							
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// song list
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			songs = new HashMap<Integer, Song>();
			songList.removeAllItems();
			
			HomeAudioSystemController hasController = new HomeAudioSystemController();
			LinkedList<Song> allSongsInLibrary = hasController.getAllSongsFromLibrary();
			
			for (Song song: allSongsInLibrary) {
				songs.put(songs.size(), song);
				songList.addItem(song.getTitle()+" - "+song.getArtist(0).getName());
			}
			selectedSong = -1;
			songList.setSelectedIndex(selectedSong);
			
			//song table
			DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
			for(int i = model.getRowCount()-1; i>=0; i--)
				model.removeRow(i);
			
			// playlist name
			playlistNameTextField.setText("");			
		}
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}
	
	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
		model.addRow(new Object[] {
				selectedSong,
				songs.get(selectedSong).getTitle(),
				songs.get(selectedSong).getArtist(0).getName()
		});
	}
	             
	private void createPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// create the playlist
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		
		ArrayList<Song> songsToAddToPlaylist = new ArrayList<>();
		// get the songs
		DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
		for (int i = 0; i < model.getRowCount(); ++i) {
			Song s = songs.get(model.getValueAt(i, 0));
			songsToAddToPlaylist.add(s);
		}	
		Song[] songsToAddArray = new Song[songsToAddToPlaylist.size()]; 
		songsToAddArray = songsToAddToPlaylist.toArray(songsToAddArray);
		
		String playlistTitle = playlistNameTextField.getText();
		
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.createPlaylist(playlistTitle, songsToAddArray);
		} catch (InvalidInputException e) {
			// TODO if error, display to user
			error = e.getMessage();
		}
	
		
		// update visuals
		refreshData();
	}
}
