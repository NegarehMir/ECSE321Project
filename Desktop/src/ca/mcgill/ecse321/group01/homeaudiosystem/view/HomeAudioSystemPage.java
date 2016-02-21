package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import java.awt.Color;
import java.sql.Time;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.group01.homeaudiosystem.controller.InvalidInputException;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Album;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Genre;
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
		
		addAlbumButton = new JButton();
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Home Audio System");
		
		albumNameLabel.setText("Album Name:");
		artistNameLabel.setText("Artist:");
		genreLabel.setText("Select Genre:");
		releaseDateLabel.setText("Release Date:");

		addAlbumButton.setText("Add Album");
		addAlbumButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlbumButtonActionPerformed(evt);
			}
		});
		
		// layout
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
						.addGroup(layout.createParallelGroup()
								.addComponent(albumNameTextField, 200, 200, 400)
								.addComponent(artistNameTextField, 200, 200, 400)
						.addGroup(layout.createParallelGroup()
								.addComponent(genreLabel)
								.addComponent(releaseDateLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(genreList)
								.addComponent(releaseDatePicker)
								.addComponent(addAlbumButton)))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addAlbumButton, genreList});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(albumNameLabel)
						.addComponent(albumNameTextField)
						.addComponent(genreLabel)
						.addComponent(genreList))
				.addGroup(layout.createParallelGroup()
						.addComponent(artistNameLabel)
						.addComponent(artistNameTextField)
						.addComponent(releaseDateLabel)
						.addComponent(releaseDatePicker))
						.addComponent(addAlbumButton)
				);
		pack();
		
	}
	
//	private void refreshData() {
//		RegistrationManager rm = RegistrationManager.getInstance();
//		
//		// error
//		errorMessage.setText(error);
//		if (error == null || error.length() == 0) {
//			// participant list
//			participants = new HashMap<Integer, Participant>();
//			participantList.removeAllItems();
//			Iterator<Participant> pIt = rm.getParticipants().iterator();
//			Integer index = 0;
//			while (pIt.hasNext()) {
//				Participant p = pIt.next();
//				participants.put(index, p);
//				participantList.addItem(p.getName());
//				index++;
//			}
//			selectedParticipant = -1;
//			participantList.setSelectedIndex(selectedParticipant);
//
//			// event list
//			events =  new HashMap<Integer, Event>();
//			eventList.removeAllItems();
//			Iterator<Event> eIt = rm.getEvents().iterator();
//			index = 0;
//			while (eIt.hasNext()) {
//				Event e = eIt.next();
//				events.put(index, e);
//				eventList.addItem(e.getName());
//				index++;
//			}
//			selectedEvent = -1;
//			eventList.setSelectedIndex(selectedEvent);
//
//
//		// participant
//			participantNameTextField.setText("");
//			// event
//			eventNameTextField.setText("");
//			eventDatePicker.getModel().setValue(null);
//			startTimeSpinner.setValue(new Date());
//			endTimeSpinner.setValue(new Date());
//
//		}
//		
//		// this is needed because the size of the window changes depending on whether an error message is shown or not
//		pack();
//	}
	private void addAlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		HomeAudioSystemController erc = new HomeAudioSystemController();
		try {
			erc.addAlbum(albumNameTextField.getText(), artistNameTextField.getText(), (java.sql.Date) releaseDatePicker.getModel().getValue(), Genre.get(selectedGenre) );
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		//refreshData();
	}
	
}


