package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Checkbox;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JSlider;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;

public class AddLocationPage extends JFrame {
	private static final long serialVersionUID = -1730646617835347222L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel locationNameLabel;
	private JTextField locationNameTextField;
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JLabel isMuteLabel;
	private Checkbox isMuteCheckbox;
	private JButton addLocationButton;
	
	// data elements
	private String error = null;

	// Creates new form AddAlbumPage
	public AddLocationPage() {
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
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Add Location");
		
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
									.addComponent(addLocationButton))
							.addGroup(layout.createParallelGroup()
									.addComponent(locationNameTextField, 100, 100, 200)
									.addComponent(volumeSlider)
									.addComponent(isMuteCheckbox)))
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
										.addComponent(addLocationButton)))
				);
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0)
		{
			locationNameTextField.setText("");
			volumeSlider.setValue(50);
			isMuteCheckbox.setState(false);
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
		
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		try {
			hasc.createLocation(locationName, volume, isMute);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
}
