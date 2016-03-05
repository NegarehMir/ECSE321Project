package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;

public class AssignPlaylistToLocationsPage extends JFrame{
	//UI elements
	private JLabel errorMessage;
	private JLabel locationLabel;
	private JTable locationsTable;
	private JScrollPane locationsScrollPane;
	private JLabel albumLabel;
	private JComboBox<String> albumList;
	private JButton assignButton;

	// data elements
	private String error = "";
	private HashMap<Integer, Location> locations;
	private Integer selectedPlaylist = -1;
	private HashMap<Integer, Playlist> playlists;

	// Creates new form AddAlbumPage
	public AssignPlaylistToLocationsPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for locations
		locationLabel = new JLabel();
		locationLabel.setText("Select Location:");
		locationsTable = new JTable(new DefaultTableModel(new Object[] { "Location", "Volume", "Is Mute" }, 0));
		locationsScrollPane = new JScrollPane(locationsTable);
		
		//elements for playlists
		albumLabel = new JLabel();
		albumLabel.setText("Select playlist:");
		albumList = new JComboBox<String>(new String[0]);
		albumList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedPlaylist = cb.getSelectedIndex();
			}
		});
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Assign Playlist to Location");
				
		assignButton = new JButton();
		assignButton.setText("Assign Playlist to Location");
		assignButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignButtonActionPerformed(evt);
			}
		});
		
		//layout
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
									.addComponent(albumLabel)
									.addComponent(locationLabel)
									.addComponent(assignButton))
							.addGroup(layout.createParallelGroup()
									.addComponent(locationsScrollPane)
									.addComponent(albumList)))
				);
		
		//rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(albumLabel)
								.addComponent(albumList))
						.addGroup(layout.createParallelGroup()
								.addComponent(locationLabel)
								.addComponent(locationsScrollPane))
						.addComponent(assignButton)
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
			locations =  new HashMap<Integer, Location>();
			for (Location location: has.getLocations()) {
				model.addRow(new Object[]{location.getName(), location.getVolume(), location.getMute()});
				locations.put(locations.size(), location);
			}
			
			// playlist list
			playlists =  new HashMap<Integer, Playlist>();
			albumList.removeAllItems();
			for (Playlist playlist: has.getPlaylists()) {
				playlists.put(playlists.size(), playlist);
				albumList.addItem(playlist.getName());
			}
			selectedPlaylist = -1;
			albumList.setSelectedIndex(selectedPlaylist);
		}
	}
	
	private void assignButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		if(locationsTable.getSelectedRows().length>0 && selectedPlaylist>-1)
		{
			for(int i: locationsTable.getSelectedRows())
			{
				Location location = locations.get(i);
				hasc.assignPlaylistToLocation(playlists.get(selectedPlaylist), location);
			}
		}
		else
		{
			if(selectedPlaylist == -1)
				error += "Please select a playlist. ";
			if(locationsTable.getSelectedRows().length==0)
			error += "Please select locations. ";
		}
		
		// update visuals
		refreshData();
	}
}
