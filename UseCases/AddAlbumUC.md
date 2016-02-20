#Title: Add Album<br />
##Actors: User<br />
##Requirements: [ML.A.01]<br /> 
##Main Scenario:<br />
	1: System shows the Add Album form<br />
	2: User enters the album title (mandatory) and its artist's name (mandatory), its genre (optional), its release date (optional), as well as a sequential list of songs.<br />
	3: System adds the album to the application<br />
	4: System shows the Add Album form<br />
##Alternatives:<br />
	3a: Either of the mandatory fields is empty or only spaces<br />
	3a1: System shows error message “Mandatory fields cannot be empty!”; use case continues at step 1.<br />
	3b: The sequential list of songs is empty or only spaces<br />
	3b1: System shows error message "List of songs must contain at least one song."; use case continues at step 1.<br />
