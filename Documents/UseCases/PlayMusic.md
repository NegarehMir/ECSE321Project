#Title: Play Music
##Actors: User
##Requirements: [HL.03] 
##Main Scenario:
	1: System shows the Play Music form
	2: User chooses an existing home location, as well as a song, an album and/or a playlist from separate drop-down lists.
	3: System adds the selected media to the playback queue of the desired home location
	4: System shows the Play Music form
##Alternatives:
	3a: No location is selected
	3a1: System shows error message “A home location must be selected.”; use case continues at step 1.
	3b: No media is selected
	3b1: System shows error message "At least one song, album or playlist must be selected"; use case continues at step 1.