Title: Add Song<br />
Actors: User<br />
Requirements: [ML.A.02]<br /> 
Main Scenario:<br />
	1: System shows the Add Song form<br />
	2: User enters the song title (mandatory), a song file and chooses from a drop-down list an album to which the song will be added<br />
	3: System adds the song to the application<br />
	4: System shows the Add Song form<br />
Alternatives:<br />
	3a: Song title is empty or only spaces<br />
	3a1: System shows error message “Song title cannot be empty!”; use case continues at step 1.<br />
	3b: The song file empty or only spaces<br />
	3b1: System shows error message "Song file cannot be empty."; use case continues at step 1.<br />