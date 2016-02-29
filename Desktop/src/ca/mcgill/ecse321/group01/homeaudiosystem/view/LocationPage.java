package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Checkbox;

import javax.swing.GroupLayout;
import javax.swing.JButton;
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

public class LocationPage extends JFrame {
	private static final long serialVersionUID = 3671940083522970643L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel locationNameLabel;
	private JTextField locationNameTextField;
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JLabel isMuteLabel;
	private Checkbox isMuteCheckbox;
	private JButton addLocationButton;
	private JLabel locationsLabel;
	private JTable locationsTable;
	private JScrollPane locationsScrollPane;
	// data elements
	private String error = null;

	// Creates new form AddAlbumPage
	public LocationPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for location name
		locationNameLabel = new JLabel();
		locationNameLabel.setText("Location Name:");
		locationNameTextField = new JTextField();
		
		//elements for volume
		volumeLabel = new JLabel();
		volumeLabel.setText("Volume:");
		volumeSlider = new JSlider();
		volumeSlider.setMinimum(0);
		volumeSlider.setMaximum(100);
		
		//elements for is mute
		isMuteLabel = new JLabel();
		isMuteLabel.setText("Is Mute:");
		isMuteCheckbox = new Checkbox();
		
		// elements for locations table
		locationsTable = new JTable(new DefaultTableModel(new Object[] { "Location", "Volume", "Is Mute" }, 0));
		locationsScrollPane = new JScrollPane(locationsTable);
		locationsLabel = new JLabel();
		locationsLabel.setText("Locations:");
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Crate Location");
		
		addLocationButton = new JButton();
		addLocationButton.setText("Add Location");
		addLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addLocationButtonActionPerformed(evt);
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
									.addComponent(locationNameLabel)
									.addComponent(volumeLabel)
									.addComponent(isMuteLabel)
									.addComponent(addLocationButton)
									.addComponent(locationsLabel))
							.addGroup(layout.createParallelGroup()
									.addComponent(locationNameTextField, 100, 100, 200)
									.addComponent(volumeSlider)
									.addComponent(isMuteCheckbox)
									.addComponent(locationsScrollPane)))
				);
		
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(locationNameLabel)
										.addComponent(locationNameTextField, 100, 100, 200))
								.addGroup(layout.createParallelGroup()
										.addComponent(volumeLabel)
										.addComponent(volumeSlider))
								.addGroup(layout.createParallelGroup()
										.addComponent(isMuteLabel)
										.addComponent(isMuteCheckbox))
								.addGroup(layout.createParallelGroup()
										.addComponent(addLocationButton))
								.addGroup(layout.createParallelGroup()
										.addComponent(locationsLabel)
										.addComponent(locationsScrollPane)))
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
			locationNameTextField.setText("");
		}
	}
	
	private void addLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//create the location
		String locationName = locationNameTextField.getText();
		int volume = volumeSlider.getValue();
		boolean isMute;
		if (volume == 0)
			isMute = true;
		else
			isMute = isMuteCheckbox.getState();
		Location location = new Location(locationName, volume, isMute);
		
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.createLocation(location);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
}
