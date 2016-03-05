package ca.mcgill.ecse321.group01.homeaudiosystem.view;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.Location;

public class ViewLocationsPage extends JFrame{
	private static final long serialVersionUID = 1527180893661940285L;

	// UI elements
	private JLabel locationsLabel;
	private JTable locationsTable;
	private JScrollPane locationsScrollPane;

	// Creates new form AddAlbumPage
	public ViewLocationsPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for locations table
		locationsTable = new JTable(new DefaultTableModel(new Object[] { "Location", "Volume", "Is Mute", "Current Song Playing"}, 0));
		locationsScrollPane = new JScrollPane(locationsTable);
		locationsLabel = new JLabel();
		locationsLabel.setText("Locations:");
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("View Locations");
		// layout
		// columns
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addComponent(locationsLabel)
					.addComponent(locationsScrollPane)
					);
				
				//rows
				layout.setVerticalGroup(
						layout.createParallelGroup()
								.addComponent(locationsLabel)
								.addComponent(locationsScrollPane)
								);
				pack();
	}
	
	private void refreshData() {
		//locations table
		DefaultTableModel model = (DefaultTableModel) locationsTable.getModel();
		for(int i = model.getRowCount()-1; i>=0; i--)
			model.removeRow(i);
		HomeAudioSystem has = HomeAudioSystem.getInstance();
		for (Location location: has.getLocations())
			model.addRow(new Object[]{location.getName(), location.getVolume(), location.getMute(),location.getCurrentSongName()});
	}
}
