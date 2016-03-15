package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class AlbumPage extends JFrame {
	private static final long serialVersionUID = 7140747775189637376L;
	
	// UI elements
	private JButton createAlbumButton;
	private JButton removeSongButton;
	private JButton viewAlbumButton;
	// Creates new form HomeAudioSystemPage
		public AlbumPage() {
			initComponents();
			//refreshData();
		}
		
		private void initComponents() {
			createAlbumButton = new JButton();
			removeSongButton = new JButton();
			viewAlbumButton = new JButton();

			// global settings and listeners
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Library");

			createAlbumButton.setText("Create Album");
			createAlbumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					createAlbumButtonActionPerformed(evt);
				}
			});
			
			removeSongButton.setText("Remove Song");
			removeSongButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					removeSongButtonActionPerformed(evt);
				}
			});
			
			viewAlbumButton.setText("View Albums");
			viewAlbumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					viewAlbumButtonActionPerformed(evt);
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
						.addComponent(createAlbumButton)
						.addComponent(removeSongButton)
						.addComponent(viewAlbumButton)
					);
			//rows
			layout.setVerticalGroup(
					layout.createSequentialGroup()
						.addComponent(createAlbumButton)
						.addComponent(removeSongButton)
						.addComponent(viewAlbumButton)
					);
			pack();
			
		}
			
		private void createAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new AddAlbumPage().setVisible(true); 
		}
		
		private void removeSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new RemoveSongPage().setVisible(true);
		}
		
		private void viewAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
			new ViewAlbumsPage().setVisible(true);
		}
}

