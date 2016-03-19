package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import java.awt.Color;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Song;

public class AddSongToAlbumPage extends JFrame{
	private static final long serialVersionUID = -812249384381425641L;
	private Album album;
	
	// UI elements
	private JLabel errorMessage;
	private JLabel albumInfoLabel;
	private	JTable songsTable;
	private	JScrollPane songScrollPane;
	private JLabel songNameLabel;
	private JTextField songNameTextField;
	private JSpinner songDurationSpinner;
	private JLabel songDurationLabel;
	private JButton addSongButton;
	private JButton addSongToAlbumButton;
	
	// data elements
	private String error = null;
	
	// Creates new form AddSongToAlbumPage
	public AddSongToAlbumPage(Album album)
	{
		this.album = album;
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		albumInfoLabel = new JLabel();
		albumInfoLabel.setText("Adding a song to album \""+album.getTitle()+"\"");

		// elements for add song
		songNameTextField = new JTextField();
		songNameLabel = new JLabel();
		songDurationLabel = new JLabel();
		songDurationSpinner = new JSpinner(new SpinnerDateModel());
		songDurationSpinner.getModel().setValue(new Date(60000)); // default to 1 minute
		JSpinner.DateEditor songDurationEditor = new JSpinner.DateEditor(songDurationSpinner, "m:ss");
		songDurationSpinner.setEditor(songDurationEditor);

		// elements for songs table
		songsTable = new JTable(new DefaultTableModel(new Object[] { "Song Title", "Duration" }, 0));
		songScrollPane = new JScrollPane(songsTable);
		songNameLabel.setText("Song Name:");
		songDurationLabel.setText("Duration:");

		addSongButton = new JButton();
		addSongToAlbumButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Add Song to Album");
		
		addSongButton.setText("Add Song");
		addSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
				model.addRow(new Object[] {
						songNameTextField.getText(), 
						new SimpleDateFormat("mm:ss").format(songDurationSpinner.getValue())
				});
			}
		});
		
		addSongToAlbumButton.setText("Add Song(s) to Album");
		addSongToAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongToAlbumButtonActionPerformed(evt);
			}
		});

		// layout
		//columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(albumInfoLabel)
						.addComponent(songScrollPane)
						.addGroup(layout.createSequentialGroup()
								.addComponent(songNameLabel)
								.addComponent(songNameTextField, 100 ,100, 200))
						.addGroup(layout.createSequentialGroup()
								.addComponent(songDurationLabel)
								.addComponent(songDurationSpinner, 50, 50, 50))
						.addGroup(layout.createSequentialGroup()
								.addComponent(addSongButton)
								.addComponent(addSongToAlbumButton)))
				);
		//rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addComponent(albumInfoLabel)
				.addComponent(songScrollPane)
				.addGroup(layout.createParallelGroup()
						.addComponent(songNameLabel)
						.addComponent(songNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(songDurationLabel)
						.addComponent(songDurationSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(addSongButton)
						.addComponent(addSongToAlbumButton))
				);
		pack();
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {			
			//track table
			DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
			for(int i = model.getRowCount()-1; i>=0; i--)
				model.removeRow(i);	
			}
	}

	private void addSongToAlbumButtonActionPerformed (java.awt.event.ActionEvent evt) {
		error = "";
		// call the controller
		HomeAudioSystemController hasc = new HomeAudioSystemController();
		if(songsTable.getRowCount()>0)
		{
			for(int i=0 ; i<songsTable.getRowCount() ; i++)
			{
				DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
				String songTitle = model.getValueAt(i, 0).toString();
				
				int songDuration = 0;
				try {
					java.util.Date date = (new SimpleDateFormat ("mm:ss")).parse((String)model.getValueAt(i, 1));
					songDuration = date.getMinutes() * 60 + date.getSeconds();
				} catch (ParseException e) {
				}
				
				Song song = new Song(songTitle, songDuration, album, album.getArtist());
				try {
					hasc.addSongToPlaylist(song, album);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			}
		}
		else
			error = "Please add a song!";
		refreshData();
	}
}
