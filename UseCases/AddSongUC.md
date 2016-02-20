#Title: Add Song
##Actors: User
##Requirements: [ML.A.02]
##Main Scenario:
	1: System shows the Add Song form
	2: User enters the song title (mandatory), a song file and chooses from a drop-down list an album to which the song will be added
	3: System adds the song to the application
	4: System shows the Add Song form
##Alternatives:
	3a: Song title is empty or only spaces
	3a1: System shows error message “Song title cannot be empty!”; use case continues at step 1.
	3b: The song file empty or only spaces
	3b1: System shows error message "Song file cannot be empty."; use case continues at step 1.