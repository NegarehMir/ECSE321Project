# Requirements

Each requirement has an associated ID. Each ID has two initial letters referring to the highest-level category (below functional and non-functional) it belongs to. Where appropriate, some IDs have a middle single letter referring to a subcategory. All IDs then have a two-digit number which distinguishes it from the other IDs which share its aforementioned letters. Periods separate each of these three components.

## Functional
### Music Library Management
#### Albums
- **[ML.A.01]** The home audio system shall allow the user to add an album by inputting the text of the album title, its artist's name, and a sequential list of songs, each with its own title and duration.
- **[ML.A.02]** The home audio system shall allow the user to add songs to an album after the album has been created.
- **[ML.A.03]** The home audio system shall allow the user to modify the song order within an album.
- **[ML.A.04]** The home audio system shall allow the user to remove an album (and by extension, all the songs associated with it) from the library.
- **[ML.A.05]** The home audio system shall allow the user to remove individual songs from the library.

#### Playlists
- **[ML.P.01]** The home audio system shall allow the user to create a playlist by selecting songs that already exist in the system.
- **[ML.P.02]** The home audio system shall allow the user to change the order of songs already added to a playlist.
- **[ML.P.03]** The home audio system shall allow the user to remove a playlist from the library, which should not delete the songs contained in the process.

### Home Location Management
- **[HL.01]** The home audio system shall allow the user to add home locations by inputting the text of the location's name.
- **[HL.02]** The home audio system shall allow the user to remove existing home locations.
- **[HL.03]** The home audio system shall allow the user to queue up songs, albums, and playlists to play next at each home location.
- **[HL.04]** The home audio system shall allow the user to remove individuals songs from the queue at each home location.
#### Volume Control
- **[HL.V.01]** The home audio system shall allow the user to increase and decrease the speaker volume at each home location.
- **[HL.V.02]** The home audio system shall allow the user to temporarily mute the speaker volume at each home location.


### Playback
- **[PB.01]** The home audio system shall keep track of the current playback position, to the nearest second, relative to the start of the song, for each song playing in each home location.
- **[PB.02]** The home audio system shall allow the user to pause and resume the currently playing song at each location.
- **[PB.03]** The home audio system shall allow the user to skip ahead to the next song in the queue while a song is paused or is playing.
#### Jumping Playback Positions
- **[PB.J.01]** When the currently playing song finishes, the next song in the queue shall start playing. If no songs are left in the queue, playback shall stop.
- **[PB.J.01]** The home audio system shall allow the user to jump back to the beginning of the currently playing song, if the user initiates the command when current playback position is five seconds or more after the song start.
- **[PB.J.01]** The home audio system shall allow the user to skip back to the previously played song,if the user initiates the command when current playback position is less than five seconds after the current song start.

## Non-Functional
