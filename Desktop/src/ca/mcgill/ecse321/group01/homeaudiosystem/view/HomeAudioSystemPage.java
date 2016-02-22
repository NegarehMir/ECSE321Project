package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import java.awt.Color;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.view.DateLabelFormatter;
import ca.mcgill.ecse321.group01.homeaudiosystem.controller.HomeAudioSystemController;

public class HomeAudioSystemPage extends JFrame {
	private static final long serialVersionUID = -8062635784771606869L;
	
	// UI elements
	private JLabel errorMessage;
	private JComboBox<String> genreList;
	private JLabel genreLabel;
	private JTextField albumNameTextField;
	private JLabel albumNameLabel;
	private JTextField artistNameTextField;
	private JLabel artistNameLabel;
	private JDatePickerImpl releaseDatePicker;
	private JLabel releaseDateLabel;
	private JButton addAlbumButton;
	private	JTable songsTable;
	private	JScrollPane songScrollPane;
	private JLabel songNameLabel;
	private JTextField songNameTextField;
	private JSpinner songDurationSpinner;
	private JLabel songDurationLabel;
	private JButton addSongButton;

	// data elements
	private String error = null;
	private Integer selectedGenre = -1;
	private HashMap<Integer, Genre> genres;
	
	// Creates new form EventRegistrationPage
	public HomeAudioSystemPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage. setForeground(Color.RED);
		
		// elements for album name
		albumNameTextField = new JTextField();
		albumNameLabel = new JLabel();
		
		// elements for artist name
		artistNameTextField = new JTextField();
		artistNameLabel = new JLabel();
				
		// elements for genre
		genreList = new JComboBox<String>(new String[0]);
		genreList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedGenre = cb.getSelectedIndex();
			}
		});
		genreLabel = new JLabel();
		
		// elements for release date
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year","Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		releaseDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		releaseDateLabel = new JLabel();
		
		// elements for add song
		songNameTextField = new JTextField();
		songNameLabel = new JLabel();
		songDurationLabel = new JLabel();
		songDurationSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor songDurationEditor = new JSpinner.DateEditor(songDurationSpinner, "m:ss");
		songDurationSpinner.setEditor(songDurationEditor); // will only show the current time

		// elements for songs table
		songsTable = new JTable(new DefaultTableModel(new Object[] { "Song Title", "Duration" }, 0));
		songScrollPane = new JScrollPane(songsTable);

		addSongButton = new JButton();
		addAlbumButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Home Audio System");
		
		albumNameLabel.setText("Album Name:");
		artistNameLabel.setText("Artist:");
		genreLabel.setText("Select Genre:");
		releaseDateLabel.setText("Release Date:");
		songNameLabel.setText("Song Name:");
		songDurationLabel.setText("Duration:");

		addSongButton.setText("Add Song");
		addSongButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSongButtonActionPerformed(evt);
			}
		});

		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlbumButtonActionPerformed(evt);
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
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(albumNameLabel)
								.addComponent(artistNameLabel)
								.addComponent(genreLabel)
								.addComponent(releaseDateLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(albumNameTextField, 200, 200, 400)
								.addComponent(artistNameTextField, 200, 200, 400)
								.addComponent(genreList)
								.addComponent(releaseDatePicker))
						.addGroup(layout.createParallelGroup()
								.addComponent(songScrollPane)
							.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(songDurationLabel)
											.addComponent(songNameLabel))
									.addGroup(layout.createParallelGroup()
										.addComponent(songNameTextField)
										.addComponent(songDurationSpinner))
									)
							.addComponent(addSongButton)
								.addComponent(addAlbumButton)))
				);
		
		//layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addAlbumButton, genreList});

		//rows
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(albumNameLabel)
								.addComponent(albumNameTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(artistNameLabel)
								.addComponent(artistNameTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(genreLabel)
								.addComponent(genreList))
						.addGroup(layout.createParallelGroup()
								.addComponent(releaseDateLabel)
								.addComponent(releaseDatePicker))
				.addGroup(layout.createSequentialGroup()
						.addComponent(songScrollPane)
						.addGroup(layout.createParallelGroup()
								.addComponent(songNameLabel)
								.addComponent(songNameTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(songDurationLabel)
								.addComponent(songDurationSpinner))
						.addComponent(addSongButton)
							.addComponent(addAlbumButton))
				);
		pack();
		
	}
	
	private void refreshData() {
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// genre list
			genres =  new HashMap<Integer, Genre>();
			genreList.removeAllItems();
			for (Genre genre: has.getGenres()) {
				genres.put(genres.size(), genre);
				genreList.addItem(genre.getName());
			}
			selectedGenre = -1;
			genreList.setSelectedIndex(selectedGenre);

			// album name
			albumNameTextField.setText("");
			
			// artist name
			artistNameTextField.setText("");
			
			// release date
			releaseDatePicker.getModel().setValue(null);

		}
		
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}
	private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController erc = new HomeAudioSystemController();
		try {
			erc.createAlbum(
					albumNameTextField.getText(), 
					artistNameTextField.getText(), 
					genres.get(selectedGenre),
					(java.sql.Date) releaseDatePicker.getModel().getValue(),
					null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}
	
	private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO: error checking
		
		//long seconds = ((java.sql.Date) songDurationSpinner.getValue()).getTime();
		DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
		model.addRow(new Object[] {
				songNameTextField.getText(), 
				new SimpleDateFormat("mm:ss").format(songDurationSpinner.getValue())
		});
	}
	

}


