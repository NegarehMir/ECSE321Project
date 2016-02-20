#Title: Add Home Location<br />
##Actors: User<br />
##Requirements: [HL.01]<br /> 
##Main Scenario:<br />
	1: System shows the Add Home Location form<br />
	2: User enters the location's name.<br />
	3: System adds the location to the application<br />
	4: System shows the Add Home Location form<br />
##Alternatives:<br />
	3a: Location name is empty or only spaces<br />
	3a1: System shows error message “Location name cannot be empty!”; use case continues at step 1.<br />