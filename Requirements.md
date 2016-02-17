# Requirements

Each requirement has an associated ID. Each ID has two initial letters referring to the highest-level category (below functional and non-functional) it belongs to. Where appropriate, some IDs have a middle single letter referring to a subcategory. All IDs then have a two-digit number which distinguishes it from the other IDs which share its aforementioned letters. Periods separate each of these three components.

Priority numbers are given before each requirement ID. Priority is given on a scale from 1 to 3, where 1 means highest priority. Generally, a priority 1 element will be something that is present in the Project Overview.

## Functional
### Music Library Management
#### Albums
- [1] **[ML.A.01]** The home audio system shall allow the user to add an album by inputting the text of the album title (mandatory) and its artist's name (mandatory), its genre (optional), its release date (optional). When inputting each album, the system shall allow the user to add sequential list of at least one song, each with its own title and duration. To add a single song that belongs to an album which hasn't been added yet, the system shall allow the user to add an album comprised of only one song.
- [3] **[ML.A.02]** The home audio system shall allow the user to add songs to an album after the album has been created.
- [3] **[ML.A.03]** The home audio system shall allow the user to modify the song order within an album.
- [2] **[ML.A.04]** The home audio system shall allow the user to remove an album (and by extension, all the songs associated with it) from the library.
- [2] **[ML.A.05]** The home audio system shall allow the user to remove individual songs from the library.

#### Playlists
- [1] **[ML.P.01]** The home audio system shall allow the user to create a playlist by selecting songs that already exist in the system.
- [3] **[ML.P.02]** The home audio system shall allow the user to change the order of songs already added to a playlist.
- [2] **[ML.P.03]** The home audio system shall allow the user to remove a playlist from the library, which should not delete the songs contained in the process.

#### User Interface
- [1] **[ML.U.01]** The home audio system shall allow the user to see all songs in its library sorted by album name or artist name.
- [2] **[ML.U.02]** The home audio system shall allow the user to see all songs in its library sorted by song title.
- [1] **[ML.U.03]** The home audio system shall allow the user to see all the songs in a given album or playlist.
- [1] **[ML.U.04]** The home audio system shall allow the user to see the artist, genre, and release date of an album.
- [1] **[ML.U.04]** The home audio system shall allow the user to see the duration, title, and album of each song.


### Home Location Management
- [1] **[HL.01]** The home audio system shall allow the user to add home locations by inputting the text of the location's name.
- [2] **[HL.02]** The home audio system shall allow the user to remove existing home locations.
- [1] **[HL.03]** The home audio system shall allow the user to add songs, albums, and playlists to a queue to play next at each home location.
- [2] **[HL.04]** The home audio system shall allow the user to remove individual songs from the queue at each home location.

#### Volume Control
- [1] **[HL.V.01]** The home audio system shall allow the user to increase and decrease the speaker volume at each home location.
- [1] **[HL.V.02]** The home audio system shall allow the user to temporarily mute the speaker volume at each home location.

#### User Interface
- [2] **[HL.U.01]** The home audio system shall allow the user to see the current volume level of each home location.
- [2] **[HL.U.02]** The home audio system shall allow the user to see each home location has been temporarily muted.
- [1] **[HL.U.03]** The home audio system shall allow the user to see the name of each home location currently added to the system.
- [2] **[HL.U.04]** The home audio system shall allow the user to see which songs have been queued up to a particular home location.


### Playback
- [2] **[PB.01]** The home audio system shall keep track of the current playback position, to the nearest second, relative to the start of the song, for each song playing in each home location.
- [2] **[PB.02]** The home audio system shall allow the user to pause and resume the currently playing song at each location.
- [2] **[PB.03]** The home audio system shall allow the user to skip ahead to the next song in the queue while a song is paused or is playing.

#### Jumping Playback Positions
- [1] **[PB.J.01]** When the currently playing song finishes, the next song in the queue shall start playing. If no songs are left in the queue, playback shall stop.
- [2] **[PB.J.02]** The home audio system shall allow the user to jump back to the beginning of the currently playing song, if the user initiates the command when current playback position is five seconds or more after the song start.
- [2] **[PB.J.03]** The home audio system shall allow the user to skip back to the previously played song,if the user initiates the command when current playback position is less than five seconds after the current song start.

#### User Interface
- [2] **[PB.U.01]** The home audio system shall allow the user to see the song currently playing in each home location, along with this song's current playback position, its title, its album's title, its artist's title, and its duration.

## Non-Functional
### Interoperability
- [1] **[IN.01]** The user may interact with the home audio system via three different applications: one for laptop/desktop machines, one for mobile devices, and one for web browsers.

#### Laptop/Desktop Application
- [1] **[IN.L.01]** The laptop/desktop application must be implemented in Java.
- [1] **[IN.L.02]** The laptop/desktop application user interface must be implemented with the Java Swing framework.

#### Mobile Application
- [1] **[IN.M.01]** The mobile application must be implemented in Java on the Android platform.
- [1] **[IN.M.02]** The mobile application user interface must be implemented using the UI development framework that comes with Android Studio.

#### Web Application
- [1] **[IN.W.01]** The web application must be primarily written in PHP.

### Speed
- [3] **[SP.01]** For the addition of a home location, album, or playlist: The time between when the user submits a correctly filled-out form and the time when the entity is added to the system should be one second or less.

### Persistence
- [1] **[PS.01]** Application data must be saved in text files (e.g., XML) or in a database.
- [1] **[PS.02]** Changes to application data made on one application shall not be recognized by the other applications.
