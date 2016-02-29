package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class HomeAudioSystemPage extends JFrame {
	private static final long serialVersionUID = -8062635784771606869L;
	
	// UI elements
	private JButton addAlbumButton;
	private JButton createPlaylistButton;
	private JButton addLocationButton;
	private JButton viewLocationsButton;
	private JButton addSongToPlaylistButton;
	
	// Creates new form HomeAudioSystemPage
	public HomeAudioSystemPage() {
		initComponents();
		//refreshData();
	}
	
	private void initComponents() {
		addAlbumButton = new JButton();
		createPlaylistButton = new JButton();
		addLocationButton = new JButton();
		viewLocationsButton = new JButton();
		addSongToPlaylistButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Home Audio System");

		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlbumButtonActionPerformed(evt);
			}
		});
		
		createPlaylistButton.setText("Create Playlist");
		createPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createPlaylistButtonActionPerformed(evt);
			}
		});
		
		addLocationButton.setText("Add Location");
		
		viewLocationsButton.setText("View Locations");
		
		addSongToPlaylistButton.setText("Add Song to Playlist");
		
		// layout
		//columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(addAlbumButton)
					.addComponent(createPlaylistButton)
					.addComponent(addLocationButton)
					.addComponent(viewLocationsButton)
					.addComponent(addSongToPlaylistButton)
				);
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(addAlbumButton)
						.addComponent(createPlaylistButton)
						.addComponent(addLocationButton)
						.addComponent(viewLocationsButton)
						.addComponent(addSongToPlaylistButton))
				);
		pack();
		
	}
		
	private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AddAlbumPage().setVisible(true); 
	}
	
	private void createPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new CreatePlaylistPage().setVisible(true);
	}

}


