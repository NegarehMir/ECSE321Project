package ca.mcgill.ecse321.group01.homeaudiosystem.application;

import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.view.HomeAudioSystemPage;

public class HomeAudioSystemApp {

	public static void main(String[] args) {
		// load model
		PersistenceHomeAudioSystem.loadHomeAudioSystemModel();

		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HomeAudioSystemPage().setVisible(true);
			}
		});
	}
}
