## Title: Add Home Location
### Actors: User
### Requirements: [HL.01]
### Main Scenario:
	1: System shows the Add Home Location form
	2: User enters the location's name.
	3: System adds the location to the application
	4: System shows the Add Home Location form
### Alternatives:
	3a: Location name is empty or only spaces
	3a1: System shows error message “Location name cannot be empty!”; use case continues at step 1.
