package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class HomeAudioSystemPage extends JFrame {
	private static final long serialVersionUID = -8062635784771606869L;
	
	// UI elements
	private JButton locationButton;
	private JButton libraryButton;
	
	// Creates new form HomeAudioSystemPage
	public HomeAudioSystemPage() {
		initComponents();
		//refreshData();
	}
	
	private void initComponents() {
		locationButton = new JButton();
		libraryButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Home Audio System");
		
		locationButton.setText("Location");
		locationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				locationButtonActionPerformed(evt);
			}
		});
		
		libraryButton.setText("Library");
		libraryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				libraryButtonActionPerformed(evt);
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
					.addComponent(locationButton)
					.addComponent(libraryButton)
				);
		//rows
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(locationButton)
						.addComponent(libraryButton))
				);
		pack();
		
	}
	
	private void locationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new LocationPage().setVisible(true);
	}

	private void libraryButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new LibraryPage().setVisible(true);
	}
}


