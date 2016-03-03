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
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;;

public class AssignSongToLocationsPage extends JFrame {
	private static final long serialVersionUID = 7404336545180673637L;

	// UI elements
	private JLabel errorMessage;
	private JLabel locationLabel;
	//private JComboBox<String> locationList;
	private JTable locationsTable;
	private JScrollPane locationsScrollPane;
	private JLabel songLabel;
	private JComboBox<String> songList;
	private JButton assignButton;

	// data elements
	private String error = "";
	private HashMap<Integer, Location> locations;
	private Integer selectedSong = -1;
	private HashMap<Integer, Song> songs;

	// Creates new form AddAlbumPage
	public AssignSongToLocationsPage() {
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
		/*locationList = new JComboBox<String>(new String[0]);
		locationList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedLocation = cb.getSelectedIndex();
			}
		});*/
		
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
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Assing song to Location");
				
		assignButton = new JButton();
		assignButton.setText("Assing song to Location");
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
									.addComponent(songLabel)
									.addComponent(locationLabel)
									.addComponent(assignButton))
							.addGroup(layout.createParallelGroup()
									.addComponent(locationsScrollPane)
									.addComponent(songList)))
				);
		
		//rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(songLabel)
								.addComponent(songList))
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
			
			// song list
			songs =  new HashMap<Integer, Song>();
			songList.removeAllItems();
			for (Song song: has.getSongs()) {
				songs.put(songs.size(), song);
				songList.addItem(song.getTitle());
			}
			selectedSong = -1;
			songList.setSelectedIndex(selectedSong);
		}
	}
	
	private void assignButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		if(locationsTable.getSelectedRows().length>0 && selectedSong>-1)
		{
			for(int i: locationsTable.getSelectedRows())
			{
				Location location = locations.get(i);
				hasc.assignSongToLocation(songs.get(selectedSong), location);
			}
		}
		else
		{
			if(selectedSong == -1)
				error += "Please select a song. ";
			if(locationsTable.getSelectedRows().length==0)
			error += "Please select locations. ";
		}
		
		// update visuals
		refreshData();
	}
}
