package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class LibraryPage extends JFrame {
	private static final long serialVersionUID = 3129526340945243081L;
	
	// UI elements
	private JButton songButton;
	private JButton albumButton;
	private JButton playlistButton;
	
	// Creates new form HomeAudioSystemPage
		public LibraryPage() {
			initComponents();
			//refreshData();
		}
		
		private void initComponents() {
			songButton = new JButton();
			albumButton = new JButton();
			playlistButton = new JButton();

			// global settings and listeners
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Library");
			
			songButton.setText("Song");
			songButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					songButtonActionPerformed(evt);
				}
			});
			
			albumButton.setText("Album");
			albumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					albumButtonActionPerformed(evt);
				}
			});
			
			playlistButton.setText("Playlist");
			playlistButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					playlistButtonActionPerformed(evt);
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
						.addComponent(songButton)
						.addComponent(albumButton)
						.addComponent(playlistButton)
					);
			//rows
			layout.setVerticalGroup(
					layout.createSequentialGroup()
							.addComponent(songButton)
							.addComponent(albumButton)
							.addComponent(playlistButton)
					);
			pack();
			
		}
		
		private void songButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new AddAlbumPage().setVisible(true); 
		}
		
		private void albumButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new AlbumPage().setVisible(true); 
		}
		
		private void playlistButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new PlaylistPage().setVisible(true);
		}
}
