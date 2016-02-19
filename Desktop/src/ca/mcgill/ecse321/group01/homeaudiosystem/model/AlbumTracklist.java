/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;
import java.sql.Date;

// line 51 "../../../../../../domainModel.umple"
// line 99 "../../../../../../domainModel.umple"
public class AlbumTracklist extends Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AlbumTracklist Associations
  private List<Song> songs;
  private Album album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AlbumTracklist(String aName, HomeAudioSystem aHomeAudioSystem, Album aAlbum, Song... allSongs)
  {
    super(aName, aHomeAudioSystem, allSongs);
    songs = new ArrayList<Song>();
    if (aAlbum == null || aAlbum.getAlbumTracklist() != null)
    {
      throw new RuntimeException("Unable to create AlbumTracklist due to aAlbum");
    }
    album = aAlbum;
  }

  public AlbumTracklist(String aName, HomeAudioSystem aHomeAudioSystem, String aTitleForAlbum, Date aReleaseDateForAlbum, Genre aGenreForAlbum, HomeAudioSystem aHomeAudioSystemForAlbum, Artist aArtistForAlbum, Song... allSongs)
  {
    super(aName, aHomeAudioSystem, allSongs);
    songs = new ArrayList<Song>();
    album = new Album(aTitleForAlbum, aReleaseDateForAlbum, aGenreForAlbum, this, aHomeAudioSystemForAlbum, aArtistForAlbum);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Song getSong(int index)
  {
    Song aSong = songs.get(index);
    return aSong;
  }

  public List<Song> getSongs()
  {
    List<Song> newSongs = Collections.unmodifiableList(songs);
    return newSongs;
  }

  public int numberOfSongs()
  {
    int number = songs.size();
    return number;
  }

  public boolean hasSongs()
  {
    boolean has = songs.size() > 0;
    return has;
  }

  public int indexOfSong(Song aSong)
  {
    int index = songs.indexOf(aSong);
    return index;
  }

  public Album getAlbum()
  {
    return album;
  }

  public boolean isNumberOfSongsValid()
  {
    boolean isValid = numberOfSongs() >= minimumNumberOfSongs();
    return isValid;
  }

  public static int minimumNumberOfSongs()
  {
    return 1;
  }

  public Song addSong(String aTitle, int aDuration, Artist aArtist)
  {
    Song aNewSong = new Song(aTitle, aDuration, aArtist, this);
    return aNewSong;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    AlbumTracklist existingAlbumTracklist = aSong.getAlbumTracklist();
    boolean isNewAlbumTracklist = existingAlbumTracklist != null && !this.equals(existingAlbumTracklist);

    if (isNewAlbumTracklist && existingAlbumTracklist.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    if (isNewAlbumTracklist)
    {
      aSong.setAlbumTracklist(this);
    }
    else
    {
      songs.add(aSong);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    //Unable to remove aSong, as it must always have a albumTracklist
    if (this.equals(aSong.getAlbumTracklist()))
    {
      return wasRemoved;
    }

    //albumTracklist already at minimum (1)
    if (numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasRemoved;
    }

    songs.remove(aSong);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addSongAt(Song aSong, int index)
  {  
    boolean wasAdded = false;
    if(addSong(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSongAt(Song aSong, int index)
  {
    boolean wasAdded = false;
    if(songs.contains(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSongAt(aSong, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (songs.size() > 0)
    {
      Song aSong = songs.get(songs.size() - 1);
      aSong.delete();
      songs.remove(aSong);
    }
    
      
    Album existingAlbum = album;
    album = null;
    if (existingAlbum != null)
    {
      existingAlbum.delete();
    }
    super.delete();
  }

}