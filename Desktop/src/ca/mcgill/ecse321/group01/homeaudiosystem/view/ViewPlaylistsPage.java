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
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Playlist;

public class ViewPlaylistsPage extends JFrame{
	private static final long serialVersionUID = -1936145343306544470L;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel playlistsLabel;
	private JTable playlistsTable;
	private JScrollPane playlistsScrollPane;
	private JButton removeButton;
	
	// data elements
	private String error = "";
	private HashMap<Integer, Playlist> playlists;
	
	// Creates new form AddAlbumPage
	public ViewPlaylistsPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for locations table
		playlistsTable = new JTable(new DefaultTableModel(new Object[] { "Playlist", "NumberOfSongs"}, 0));
		playlistsScrollPane = new JScrollPane(playlistsTable);
		playlistsLabel = new JLabel();
		playlistsLabel.setText("Playlists:");
		
		removeButton = new JButton();
		removeButton.setText("Remove Playlist");
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeButtonActionPerformed(evt);
			}
		});
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("View Playlists");
		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(errorMessage)
					.addComponent(playlistsLabel)
					.addComponent(playlistsScrollPane)
					.addComponent(removeButton)
					);
				
				//rows
				layout.setVerticalGroup(
						layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(playlistsLabel)
								.addComponent(playlistsScrollPane)
								)
						.addComponent(removeButton));
				pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			//playlists table			
			DefaultTableModel model = (DefaultTableModel) playlistsTable.getModel();
			for(int i = model.getRowCount()-1; i>=0; i--)
				model.removeRow(i);
			HomeAudioSystem has = HomeAudioSystem.getInstance();
			playlists =  new HashMap<Integer, Playlist>();
			for (Playlist playlist: has.getPlaylists())
			{
				if(!(playlist instanceof Album ))
				model.addRow(new Object[]{playlist.getTitle(), playlist.getSongs().size()});
				playlists.put(playlists.size(), playlist);
			}
		}
	}
	
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		if(playlistsTable.getSelectedRows().length>0)
		{
			for(int i: playlistsTable.getSelectedRows())
			{
				Playlist playlist = playlists.get(i);
				hasc.removePlaylist(playlist);
			}			
		}
		else
			error = "Please choose a playlist!";
		
		// update visuals
		refreshData();
	}
}
