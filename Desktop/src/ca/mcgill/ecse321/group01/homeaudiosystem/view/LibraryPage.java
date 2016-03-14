package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class LibraryPage extends JFrame {
	private static final long serialVersionUID = 3129526340945243081L;
	
	// UI elements
	private JButton addAlbumButton;
	private JButton playlistButton;
	// Creates new form HomeAudioSystemPage
		public LibraryPage() {
			initComponents();
			//refreshData();
		}
		
		private void initComponents() {
			addAlbumButton = new JButton();
			playlistButton = new JButton();

			// global settings and listeners
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Library");

			addAlbumButton.setText("Add Album");
			addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addAlbumButtonActionPerformed(evt);
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
						.addComponent(addAlbumButton)
						.addComponent(playlistButton)
					);
			//rows
			layout.setVerticalGroup(
					layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
							.addComponent(addAlbumButton)
							.addComponent(playlistButton))
					);
			pack();
			
		}
			
		private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new AddAlbumPage().setVisible(true); 
		}
		
		private void playlistButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new PlaylistPage().setVisible(true);
		}
}
