package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;

public class LocationPage extends JFrame {
	private static final long serialVersionUID = 3671940083522970643L;

	// UI elements
	private JButton addLocationButton;
	private JButton modifyLocationButton;
	private JButton viewLocationsButton;
	private JLabel assignLabel;
	private JButton assignPlaylistButton;
	private JButton assignAlbumButton;
	private JButton assignSongButton;

	// Creates new form AddAlbumPage
	public LocationPage() {
		initComponents();
	}

	private void initComponents() {		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Location");
		
		addLocationButton = new JButton();
		addLocationButton.setText("Add Location");
		addLocationButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addLocationButtonActionPerformed(evt);
			}
		});
		
		modifyLocationButton = new JButton();
		modifyLocationButton.setText("Modify Location");
		modifyLocationButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyLocationButtonActionPerformed(evt);
			}
		});
		
		viewLocationsButton = new JButton();
		viewLocationsButton.setText("View Locations");
		viewLocationsButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewLocationsButtonActionPerformed(evt);
			}
		});
		
		// elements of assign
		assignLabel = new JLabel();
		assignLabel.setText("Assign to Location");
		
		assignPlaylistButton = new JButton();
		assignPlaylistButton.setText("Assign A Playlist");
		assignPlaylistButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignPlaylistButtonActionPerformed(evt);
			}
		});
		
		assignAlbumButton = new JButton();
		assignAlbumButton.setText("Assign An Album");
		assignAlbumButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignAlbumButtonActionPerformed(evt);
			}
		});
		
		assignSongButton = new JButton();
		assignSongButton.setText("Assign A Song");
		assignSongButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignSongButtonActionPerformed(evt);
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
							.addComponent(addLocationButton)
							.addComponent(modifyLocationButton)
							.addComponent(viewLocationsButton)
							.addComponent(assignLabel))
					.addGroup(layout.createParallelGroup()
							.addComponent(assignSongButton)
							.addComponent(assignAlbumButton)
							.addComponent(assignPlaylistButton))
							);
		
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(addLocationButton)
						.addComponent(modifyLocationButton)
						.addComponent(viewLocationsButton)
						.addGroup(layout.createParallelGroup()
								.addComponent(assignLabel)
								.addComponent(assignSongButton))
						.addComponent(assignAlbumButton)
						.addComponent(assignPlaylistButton))
				);
		pack();
	}
	
	private void addLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AddLocationPage().setVisible(true);	
	}
	
	private void modifyLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new ModifyLocationPage().setVisible(true);
	}
	
	private void viewLocationsButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new ViewLocationsPage().setVisible(true);
	}
	
	private void assignPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AssignPlaylistToLocationsPage().setVisible(true);
			
	}
	
	private void assignAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AssignAlbumToLocationsPage().setVisible(true);
	}
	
	private void assignSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AssignSongToLocationsPage().setVisible(true);
	}
}
