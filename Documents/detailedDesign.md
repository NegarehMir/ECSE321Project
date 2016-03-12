# Detailed Design Description

In this section of text and the class diagrams that follow, we describe the detailed design of the model, view, controller, and persistence subsystems. We'll discuss the key classes in-depth:
- HomeAudioSystem
- LocationPage
- LibraryPage
- PersistenceHomeAudioSystem

### HomeAudioSystem
Contains instances of all the model classes for eventual storage and retreival through the persistence layers.

### LocationPage
View component that allows the user (ultimately) to access all operations pertaining to locations, including adding music to stream.

### LibraryPage
View component that allows the user (ultimately) to access all operations pertaining to the music library, including adding and deleting music.

### PersistenceHomeAudioSystem
Main interface through which application data (via an instance of the HomeAudioSystem class) is retrieve on application start. It is one of two primary classes comprising the persistence layer.
