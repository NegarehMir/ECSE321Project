package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;

import java.awt.Checkbox;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;

public class ModifyLocationPage extends JFrame {
	private static final long serialVersionUID = -2473586472668543041L;

	// UI elements
	private JLabel errorMessage;
	private JLabel locationsLabel;
	private JComboBox<String> locationList;
	private JLabel locationNameLabel;
	private JLabel selectedLocationNameLabel;
	private JLabel volumeLabel;
	private JSlider selectedVolumeSlider;
	private JLabel isMuteLabel;
	private Checkbox selectedIsMuteCheckbox;
	private JButton modifyLocationButton;

	// data elements
	private String error = null;
	private Integer selectedLocation = -1;
	private HashMap<Integer, Location> locations;

	// Creates new form AddAlbumPage
	public ModifyLocationPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for selected location
		locationNameLabel = new JLabel();
		locationNameLabel.setText("Selected location:");
		selectedLocationNameLabel = new JLabel();
		selectedLocationNameLabel.setText("");
		volumeLabel = new JLabel();
		volumeLabel.setText("Volume:");
		selectedVolumeSlider = new JSlider();
		isMuteLabel = new JLabel();
		isMuteLabel.setText("Is Mute");
		selectedIsMuteCheckbox = new Checkbox();
		
		//elements for location list
		locationsLabel = new JLabel();
		locationsLabel.setText("Locations:");
		locationList = new JComboBox<String>(new String[0]);
		locationList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedLocation = cb.getSelectedIndex();
				if(selectedLocation>-1)
				{
					Location location = locations.get(selectedLocation);
					selectedLocationNameLabel.setText(location.getName());
					selectedVolumeSlider.setValue(location.getVolume());
					selectedIsMuteCheckbox.setState(location.getMute());
				}
			}
		});

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Modify Location");

		modifyLocationButton = new JButton();
		modifyLocationButton.setText("Modify Location");
		modifyLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyLocationButtonActionPerformed(evt);
			}
		});

		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(locationsLabel)
								.addComponent(locationNameLabel)
								.addComponent(selectedLocationNameLabel)
								.addComponent(modifyLocationButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(locationList)
						.addComponent(volumeLabel)
						.addComponent(selectedVolumeSlider))
				.addGroup(layout.createParallelGroup()
						.addComponent(isMuteLabel)
						.addComponent(selectedIsMuteCheckbox)))
		);

		// rows
		layout.setVerticalGroup(
				layout.createSequentialGroup().addComponent(errorMessage)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(locationsLabel)
										.addComponent(locationList))
						.addGroup(layout.createParallelGroup()
								.addComponent(locationNameLabel)
								.addComponent(volumeLabel)
								.addComponent(isMuteLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(selectedLocationNameLabel)
								.addComponent(selectedVolumeSlider)
								.addComponent(selectedIsMuteCheckbox))
						.addComponent(modifyLocationButton))
		);
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			// location list
			locations = new HashMap<Integer, Location>();
			locationList.removeAllItems();
			for (Location location : has.getLocations()) {
				locations.put(locations.size(), location);
				locationList.addItem(location.getName());
			}
			selectedLocation = -1;
			locationList.setSelectedIndex(selectedLocation);
			
			// selected location
			selectedLocationNameLabel.setText("");
			selectedVolumeSlider.setValue(50);
			selectedIsMuteCheckbox.setState(false);
		}
	}

	private void modifyLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Location oldLocation = locations.get(selectedLocation);
		
		String locationName ="";
		int newVolume = 0;
		boolean newMute = false;
		if(oldLocation != null)
		{
			locationName = oldLocation.getName();
			newVolume = selectedVolumeSlider.getValue();
			newMute = selectedIsMuteCheckbox.getState();
		}
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.modifyLocation(oldLocation, locationName, newVolume, newMute);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
	}
}
