#Title: Create PLaylist
##Actors: User
##Requirements: [ML.P.01]
##Main Scenario:
	1: System shows the Create PLaylist form
	2: User enters the playlist name, and chooses songs from a list of all songs in the system
	3: System adds the playlist to the application
	4: System shows the Create Playlist form
##Alternatives:
	3a: Playlist name is empty or only spaces
	3a1: System shows error message “Playlist name cannot be empty!”; use case continues at step 1.
	3b: No song is selected
	3b1: System shows error message "To create a playlist, at least one song must be selected."; use case continues at step 1.