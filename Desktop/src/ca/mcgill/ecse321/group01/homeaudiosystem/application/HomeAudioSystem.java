package ca.mcgill.ecse321.group01.homeaudiosystem.application;

import ca.mcgill.ecse321.group01.homeaudiosystem.persistence.PersistenceHomeAudioSystem;
import ca.mcgill.ecse321.group01.homeaudiosystem.view.HomeAudioSystemPage;
import ca.mcgill.ecse321.group01.homeaudiosystem.model.*;

public class HomeAudioSystem {

	public static void main(String[] args) {
		// load model
		PersistenceHomeAudioSystem.loadHomeAudioSystemModel();

		ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem has =
			ca.mcgill.ecse321.group01.homeaudiosystem.model.HomeAudioSystem.getInstance();

		// prepopulate generes if there aren't any at all
		if (has.getGenres().size() <= 0) {
			String[] genreNames = {
					"Dubstep",
					"Classical",
					"Rock",
					"Jazz",
			};
			for(String name: genreNames) {
				has.addGenre(new Genre(name));
			}
		}

		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HomeAudioSystemPage().setVisible(true);
			}
		});

	}

}
