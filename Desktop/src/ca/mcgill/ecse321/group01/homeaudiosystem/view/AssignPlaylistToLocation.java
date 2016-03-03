/*package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Checkbox;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSlider;
import javax.swing.JTable;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class AssignPlaylistToLocation extends JFrame {
	private static final long serialVersionUID = 3671940083522970643L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel playlistLabel;
	private JComboBox<String> playlistList;	
	private JLabel locationsLabel;
	private JTable locationsTable;
	private JScrollPane locationsScrollPane;
	private JButton assignPlaylistToLocationButton;
	
	// data elements
	private String error = null;
	private Integer selectedPlaylist = -1;
	private HashMap<Integer, Playlist> playlists;
	private Integer selectedLocation = -1;

	// Creates new form AddAlbumPage
	public AssignPlaylistToLocation() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for playlists
		playlistLabel = new JLabel();
		playlistLabel.setText("Select Playlist:");
		playlistList = new JComboBox<String>(new String[0]);
		playlistList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
			}
		});
		
		// elements for locations table
		locationsTable = new JTable(new DefaultTableModel(new Object[] { "Location", "Volume", "Is Mute" }, 0));
		locationsScrollPane = new JScrollPane(locationsTable);
		locationsScrollPane.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
			}
		});
		locationsLabel = new JLabel();
		locationsLabel.setText("Locations:");
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Assing Playlist to Location");
		
		assignPlaylistToLocationButton = new JButton();
		assignPlaylistToLocationButton.setText("Assing Playlist to Location");
		assignPlaylistToLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignPlaylistToLocationButtonActionPerformed(evt);
			}
		});
		
		
		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(errorMessage)
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(playlistLabel)
									.addComponent(locationsLabel)
									.addComponent(assignPlaylistToLocationButton))
							.addGroup(layout.createParallelGroup()
									.addComponent(playlistList)
									.addComponent(locationsScrollPane)))
				);
		
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(playlistLabel)
										.addComponent(playlistList))
								.addGroup(layout.createParallelGroup()
										.addComponent(locationsLabel)
										.addComponent(locationsScrollPane))
								.addComponent(assignPlaylistToLocationButton))
				);
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0)
		{
			//locations table
			DefaultTableModel model = (DefaultTableModel) locationsTable.getModel();
			for(int i = model.getRowCount()-1; i>=0; i--)
				model.removeRow(i);
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			for (Location location: has.getLocations()) {
				model.addRow(new Object[]{location.getName(), location.getVolume(), location.getMute()});
			}
			
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
	}
	             
	private void assignPlaylistToLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.assignPlaylistToLocation(
					playlists.get(selectedPlaylist), 
					location);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
}*/