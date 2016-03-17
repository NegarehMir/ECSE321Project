package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class AlbumPage extends JFrame {
	private static final long serialVersionUID = 7140747775189637376L;

	// UI elements
	private JLabel errorMessage;
	private JLabel albumLabel;
	private JComboBox<String> albumsList;
	private JLabel songsLabel;
	private JTable songsTable;
	private JScrollPane songsScrollPane;
	private JButton addSongButton;
	private JButton addAlbumButton;
	private JButton removeSongButton;
	private JButton removeAlbumButton;

	// data elements
	private String error = "";
	private HashMap<Integer, Song> songs;
	private Integer selectedAlbum = -1;
	private HashMap<Integer, Album> albums;

	// Creates new form AlbumPage
	public AlbumPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for songs
		songsLabel = new JLabel();
		songsLabel.setText("Select Song:");
		songsTable = new JTable(new DefaultTableModel(new Object[] { "Song Title", "Artist", "Duration" }, 0));
		songsScrollPane = new JScrollPane(songsTable);
		
		// elements for albums
		albumLabel = new JLabel();
		albumLabel.setText("Select Album:");
		albumsList = new JComboBox<String>(new String[0]);
		albumsList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedAlbum = cb.getSelectedIndex();
				refreshSongs();
			}
		});

		addAlbumButton = new JButton();
		addSongButton = new JButton();
		removeAlbumButton = new JButton();
		removeSongButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Album");

		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlbumButtonActionPerformed(evt);
			}
		});
		
		addSongButton.setText("Add Song");
		addSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongButtonActionPerformed(evt);
			}
		});

		removeSongButton.setText("Remove Song");
		removeSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeSongButtonActionPerformed(evt);
			}
		});

		removeAlbumButton.setText("Remove  Album");
		removeAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAlbumButtonActionPerformed(evt);
			}
		});

		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(albumLabel)
						.addComponent(songsLabel)
						.addComponent(addAlbumButton)
						.addComponent(removeAlbumButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(albumsList)
						.addComponent(songsScrollPane)
						.addComponent(addSongButton)
						.addComponent(removeSongButton)));
		// rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(albumLabel)
						.addComponent(albumsList))
				.addGroup(layout.createParallelGroup()
						.addComponent(songsLabel)
						.addComponent(songsScrollPane))
				.addGroup(layout.createParallelGroup()
						.addComponent(addAlbumButton)
						.addComponent(addSongButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(removeAlbumButton)
						.addComponent(removeSongButton)));
		pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0)
		{
			// album list
			albums =  new HashMap<Integer, Album>();
			albumsList.removeAllItems();
			HomeAudioSystemController hasc = new HomeAudioSystemController();
			for (Album album: hasc.getAllAlbumsFromLibrary()) {
				albums.put(albums.size(), album);
				albumsList.addItem(album.getTitle());
			}
			selectedAlbum = -1;
			albumsList.setSelectedIndex(selectedAlbum);
		}
	}
	
	private void refreshSongs() {
		DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
		for(int i = model.getRowCount()-1; i>=0; i--)
			model.removeRow(i);
		if(selectedAlbum >= 0)
		{
			songs =  new HashMap<Integer, Song>();
			Album album = albums.get(selectedAlbum);
			for (Song song: album.getSongs()) {
				Object[] newRow = new Object[3];
				newRow[0] = song.getTitle();
			
				newRow[1] = song.getArtist(0).getName();
				if(song.getArtists().size()==2)
					newRow[1] += ", " + song.getArtist(1).getName();
				else if(song.getArtists().size()>2)
					newRow[1] += ", " + song.getArtist(1).getName()+" ...";
			
				int duration = song.getDuration();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
				date.setSeconds(duration%60);
				date.setMinutes(duration/60);
				newRow[2] = formatter.format(date);
			
				model.addRow(newRow);
				songs.put(songs.size(), song);
			}
		}
	}
	
	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Album album = albums.get(selectedAlbum);
		new AddSongToAlbumPage(album).setVisible(true);
	}
	
	private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AddAlbumPage().setVisible(true);
	}

	private void removeSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if(songsTable.getSelectedRows().length>0)
		{
			JFrame frame = new JFrame("Warning");
			int warning = JOptionPane.showConfirmDialog(
				    frame,
				    "Are you sure you want to delete the selected song(s)?",
				    "Warning",
				    JOptionPane.YES_NO_OPTION);
			
			if (warning == JOptionPane.YES_OPTION) {
				// call the controller
				HomeAudioSystemController hasc = new HomeAudioSystemController();
				for(int i: songsTable.getSelectedRows())
				{
					Song song = songs.get(i);
					try {
						hasc.removeSong(song);
						songs.remove(song);
					}
					catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
			}
		}
		else {
			Song song = null;
			try {
				HomeAudioSystemController hasc = new HomeAudioSystemController();
				hasc.removeSong(song);
			}
			catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
			
		refreshData();
	}
	
	private void removeAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		JFrame frame = new JFrame("Warning");
		int warning = JOptionPane.showConfirmDialog(
			    frame,
			    "Are you sure you want to delete this album?",
			    "Warning",
			    JOptionPane.YES_NO_OPTION);
		
		if (warning == JOptionPane.YES_OPTION) {
			Album album = albums.get(selectedAlbum);
			
			// call the controller
			HomeAudioSystemController hasc = new HomeAudioSystemController();
			hasc.removePlaylist(album);
			albums.remove(album);
			
			refreshData();
		} 
	}
}
