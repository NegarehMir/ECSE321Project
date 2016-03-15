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
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class RemoveSongPage extends JFrame{
	private static final long serialVersionUID = -8962943870829355400L;
	
	//UI elements
	private JLabel errorMessage;
	private JLabel songLabel;
	private JComboBox<String> songList;
	private JButton removeSongButton;
	
	// data elements
	private String error = null;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;
	
	// Creates new form AddAlbumPage
	public RemoveSongPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
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
				
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Remove Song");
		
		removeSongButton = new JButton();
		removeSongButton.setText("Remove Song");
		removeSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongButtonActionPerformed(evt);
			}
		});
		
		// layout
		//columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(songLabel)
						.addComponent(removeSongButton))
				.addComponent(songList)
				);

		//rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(songLabel)
						.addComponent(songList))
				.addComponent(removeSongButton)
				);
		pack();							
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// song list
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
		}
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}
	
	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Song song = songs.get(selectedSong);
		
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.removeSong(song);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
	}
}
