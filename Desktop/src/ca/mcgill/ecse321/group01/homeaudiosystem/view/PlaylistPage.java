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
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class PlaylistPage extends JFrame {
	private static final long serialVersionUID = 7140747775189637376L;

	// UI elements
	private JLabel errorMessage;
	private JLabel playlistLabel;
	private JComboBox<String> playlistsList;
	private JLabel songsLabel;
	private JTable songsTable;
	private JScrollPane songsScrollPane;
	private JButton moveUpButton;
	private JButton moveDownButton;
	private JButton addSongButton;
	private JButton addPlaylistButton;
	private JButton removeSongButton;
	private JButton removePlaylistButton;
	private JButton refreshButton;

	// data elements
	private String error = "";
	private HashMap<Integer, Song> songs;
	private Integer selectedPlaylist = -1;
	private HashMap<Integer, Playlist> playlists;

	// Creates new form PlaylistPage
	public PlaylistPage() {
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

		// elements for playlists
		playlistLabel = new JLabel();
		playlistLabel.setText("Select Playlist:");
		playlistsList = new JComboBox<String>(new String[0]);
		playlistsList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
				refreshSongs();
			}
		});
		
		moveUpButton = new JButton();
		moveDownButton = new JButton();
		addPlaylistButton = new JButton();
		addSongButton = new JButton();
		removePlaylistButton = new JButton();
		removeSongButton = new JButton();
		refreshButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Playlist");
		
		moveUpButton.setVisible(false);
		moveUpButton.setText("Move Song Up");
		moveUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveUpButtonActionPerformed(evt);
			}
		});
		
		moveDownButton.setVisible(false);
		moveDownButton.setText("Move Song Down");
		moveDownButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveDownButtonActionPerformed(evt);
			}
		});
		
		addPlaylistButton.setText("Add Playlist");
		addPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addPlaylistButtonActionPerformed(evt);
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

		removePlaylistButton.setText("Remove  Playlist");
		removePlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removePlaylistButtonActionPerformed(evt);
			}
		});
		
		refreshButton.setText("Refresh");
		refreshButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshData();
			}
		});

		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(playlistLabel)
						.addComponent(songsLabel)
						.addComponent(moveUpButton)
						.addComponent(addPlaylistButton)
						.addComponent(removePlaylistButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(playlistsList)
						.addComponent(songsScrollPane)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(moveDownButton)
										.addComponent(addSongButton)
										.addComponent(removeSongButton))
								.addGap(260)
								.addComponent(refreshButton)))
				);
		// rows
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(playlistLabel)
						.addComponent(playlistsList))
				.addGroup(layout.createParallelGroup()
						.addComponent(songsLabel)
						.addComponent(songsScrollPane))
				.addGroup(layout.createParallelGroup()
						.addComponent(moveUpButton)
						.addComponent(moveDownButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(addPlaylistButton)
						.addComponent(addSongButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(removePlaylistButton)
						.addComponent(removeSongButton)
						.addComponent(refreshButton))
				);
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// playlist list
			playlists = new HashMap<Integer, Playlist>();
			playlistsList.removeAllItems();
			HomeAudioSystemController hasc = new HomeAudioSystemController();
			for (Playlist playlist : hasc.getAllPlaylistsFromLibrary()) {
				if(!(playlist instanceof Album ))
				{
					playlists.put(playlists.size(), playlist);
					playlistsList.addItem(playlist.getTitle());
				}
			}
			playlistsList.setSelectedIndex(selectedPlaylist);
		}
		pack();
	}

	private void refreshSongs() {
		DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
		for (int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
		if (selectedPlaylist >= 0) {
			songs = new HashMap<Integer, Song>();
			Playlist playlist = playlists.get(selectedPlaylist);
			for (Song song : playlist.getSongs()) {
				Object[] newRow = new Object[3];
				newRow[0] = song.getTitle();

				newRow[1] = song.getArtist(0).getName();
				if (song.getArtists().size() == 2)
					newRow[1] += ", " + song.getArtist(1).getName();
				else if (song.getArtists().size() > 2)
					newRow[1] += ", " + song.getArtist(1).getName() + " ...";

				int duration = song.getDuration();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
				date.setSeconds(duration % 60);
				date.setMinutes(duration / 60);
				newRow[2] = formatter.format(date);

				model.addRow(newRow);
				songs.put(songs.size(), song);
			}
			moveUpButton.setVisible(true);
			moveDownButton.setVisible(true);
		}
		else
		{
			moveUpButton.setVisible(false);
			moveDownButton.setVisible(false);
		}
			
	}
	
	private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (songsTable.getSelectedRows().length > 0) {
			for(int position : songsTable.getSelectedRows())
			{
				Playlist playlist = playlists.get(selectedPlaylist);
				// call the controller
				HomeAudioSystemController hasc = new HomeAudioSystemController();
				try {
					hasc.moveSongUpInPlaylist(playlist, position);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			}
		}
		refreshData();
	}
	
	private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (songsTable.getSelectedRows().length > 0) {
			// making sure no selected song is the last song since it cannot be moved down
			for(int position : songsTable.getSelectedRows())
			{
				if (position == songs.size()-1)
				{
					error = "Song already at the bottom!";
					break;
				}
			}
			if(error.length()==0)
			{
				for(int position : songsTable.getSelectedRows())
				{
					Playlist playlist = playlists.get(selectedPlaylist);
					// call the controller
					HomeAudioSystemController hasc = new HomeAudioSystemController();
					try {
						hasc.moveSongDownInPlaylist(playlist, position);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
			}
		}
		refreshData();
	}
	
	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Playlist playlist = playlists.get(selectedPlaylist);
		new AddSongToPlaylistPage(playlist).setVisible(true);
	}

	private void addPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AddPlaylistPage().setVisible(true);
	}

	private void removeSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (songsTable.getSelectedRows().length > 0) {
			JFrame frame = new JFrame("Warning");
			int warning = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the selected song(s)?",
					"Warning", JOptionPane.YES_NO_OPTION);

			if (warning == JOptionPane.YES_OPTION) {
				// call the controller
				HomeAudioSystemController hasc = new HomeAudioSystemController();
				for (int i : songsTable.getSelectedRows()) {
					Playlist playlist = playlists.get(selectedPlaylist);
					Song song = songs.get(i);
					try {
						hasc.removeSongFromPlaylist(playlist, i);
						songs.remove(song);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
			}
		} else {
			Playlist playlist = null;
			try {
				HomeAudioSystemController hasc = new HomeAudioSystemController();
				hasc.removeSongFromPlaylist(playlist, -1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		refreshData();
	}

	private void removePlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		JFrame frame = new JFrame("Warning");
		int warning = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this playlist?", "Warning",
				JOptionPane.YES_NO_OPTION);

		if (warning == JOptionPane.YES_OPTION) {
			Playlist playlist = playlists.get(selectedPlaylist);

			// call the controller
			HomeAudioSystemController hasc = new HomeAudioSystemController();
			hasc.removePlaylist(playlist);
			playlists.remove(playlist);

			refreshData();
		}
	}
}
