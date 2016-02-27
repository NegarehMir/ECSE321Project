#Title: Playback
##Actors: User
##Requirements: [HL.V.01], [HL.V.02], [PB.02]  
##Main Scenario:
	1: System shows the Playback form
	2: User chooses an existing home location, and sets a volume, mutes, unmutes, pauses playback or resumes playback.
	3: System modifies the playback parameters of the desired home location
	4: System shows the Playback form
##Alternatives:
	3a: No location is selected
	3a1: System shows error message “A home location must be selected.”; use case continues at step 1.
	3b: No action is selected
	3b1: System shows error message "One action must be selected."; use case continues at step 1.
	3c: More than one action is selected
	3c1: System shows error message "Only one action can be selected."; use case continues at step 1.