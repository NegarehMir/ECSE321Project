package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;

public class ViewAlbumsPage extends JFrame{
	private static final long serialVersionUID = -9132660697660273897L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel albumsLabel;
	private JTable albumsTable;
	private JScrollPane albumsScrollPane;
	private JButton removeButton;
	
	// data elements
	private String error = "";
	private HashMap<Integer, Album> albums;
	
	// Creates new form AddAlbumPage
	public ViewAlbumsPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for locations table
		albumsTable = new JTable(new DefaultTableModel(new Object[] { "Album", "NumberOfSongs"}, 0));
		albumsScrollPane = new JScrollPane(albumsTable);
		albumsLabel = new JLabel();
		albumsLabel.setText("Albums:");
		
		removeButton = new JButton();
		removeButton.setText("Remove Album");
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeButtonActionPerformed(evt);
			}
		});
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("View Albums");
		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(errorMessage)
					.addComponent(albumsLabel)
					.addComponent(albumsScrollPane)
					.addComponent(removeButton)
					);
				
				//rows
				layout.setVerticalGroup(
						layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(albumsLabel)
								.addComponent(albumsScrollPane)
								)
						.addComponent(removeButton));
				pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			//albums table			
			DefaultTableModel model = (DefaultTableModel) albumsTable.getModel();
			for(int i = model.getRowCount()-1; i>=0; i--)
				model.removeRow(i);
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			albums =  new HashMap<Integer, Album>();
			for (Playlist playlist: has.getPlaylists())
			{
				if(playlist instanceof Album)
				{
					model.addRow(new Object[]{playlist.getTitle(), playlist.getSongs().size()});
					albums.put(albums.size(), (Album) playlist);
				}
			}
		}
	}
	
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		if(albumsTable.getSelectedRows().length>0)
		{
			for(int i: albumsTable.getSelectedRows())
			{
				Album album = albums.get(i);
				hasc.removePlaylist(album);
			}			
		}
		else
			error = "Please choose a album!";
		
		// update visuals
		refreshData();
	}
}
