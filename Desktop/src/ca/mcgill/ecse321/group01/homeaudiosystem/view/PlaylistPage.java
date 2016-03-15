package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PlaylistPage extends JFrame {
	private static final long serialVersionUID = 4361869775884073174L;
	
	// UI elements
	private JButton createPlaylistButton;
	private JButton addSongToPlaylistButton;
	private JButton viewPlaylistButton;
	// Creates new form HomeAudioSystemPage
		public PlaylistPage() {
			initComponents();
			//refreshData();
		}
		
		private void initComponents() {
			createPlaylistButton = new JButton();
			addSongToPlaylistButton = new JButton();
			viewPlaylistButton = new JButton();

			// global settings and listeners
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Library");

			createPlaylistButton.setText("Create Playlist");
			createPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					createPlaylistButtonActionPerformed(evt);
				}
			});
			
			addSongToPlaylistButton.setText("Add Song to Playlist");
			addSongToPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addSongToPlaylistButtonActionPerformed(evt);
				}
			});
			
			viewPlaylistButton.setText("View Playlists");
			viewPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					viewPlaylistButtonActionPerformed(evt);
				}
			});
			
			// layout
			//columns
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup(
					layout.createParallelGroup()
						.addComponent(createPlaylistButton)
						.addComponent(addSongToPlaylistButton)
						.addComponent(viewPlaylistButton)
					);
			//rows
			layout.setVerticalGroup(
					layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
							.addComponent(createPlaylistButton)
							.addComponent(addSongToPlaylistButton)
							.addComponent(viewPlaylistButton))
					);
			pack();
			
		}
			
		private void createPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new CreatePlaylistPage().setVisible(true); 
		}
		
		private void addSongToPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new AddSongToPlaylistPage().setVisible(true);
		}
		
		private void viewPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new ViewPlaylistsPage().setVisible(true);
		}
}
