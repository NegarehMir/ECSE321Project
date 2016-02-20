Title: Add Album
Actors: User
Requirements: [ML.A.01] 
Main Scenario:
	1: System shows the Add Album form
	2: User enters the album title (mandatory) and its artist's name (mandatory), its genre (optional), its release date (optional), as well as a sequential list of songs.
	3: System adds the album to the application
	4: System shows the Add Album form
Alternatives:
	3a: Either of the mandatory fields is empty or only spaces
	3a1: System shows error message “Mandatory fields cannot be empty!”; use case continues at step 1.
	3b: The sequential list of songs is empty or only spaces
	3b1: System shows error message "List of songs must contain at least one song."; use case continues at step 1.
