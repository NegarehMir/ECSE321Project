/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-2f66a7f modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;
import java.sql.Date;

// line 36 "../../../../../../../../../ump/tmp960453/model.ump"
// line 77 "../../../../../../../../../ump/tmp960453/model.ump"
public class Artist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private String name;

  //Artist Associations
  private List<Album> albums;
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artist(String aName)
  {
    name = aName;
    albums = new ArrayList<Album>();
    songs = new ArrayList<Song>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Album getAlbum(int index)
  {
    Album aAlbum = albums.get(index);
    return aAlbum;
  }

  public List<Album> getAlbums()
  {
    List<Album> newAlbums = Collections.unmodifiableList(albums);
    return newAlbums;
  }

  public int numberOfAlbums()
  {
    int number = albums.size();
    return number;
  }

  public boolean hasAlbums()
  {
    boolean has = albums.size() > 0;
    return has;
  }

  public int indexOfAlbum(Album aAlbum)
  {
    int index = albums.indexOf(aAlbum);
    return index;
  }

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

  public static int minimumNumberOfAlbums()
  {
    return 0;
  }

  public Album addAlbum(String aTitle, Date aReleaseDate, Genre aGenre, AlbumTracklist aAlbumTracklist)
  {
    return new Album(aTitle, aReleaseDate, aGenre, this, aAlbumTracklist);
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    Artist existingArtist = aAlbum.getArtist();
    boolean isNewArtist = existingArtist != null && !this.equals(existingArtist);
    if (isNewArtist)
    {
      aAlbum.setArtist(this);
    }
    else
    {
      albums.add(aAlbum);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    //Unable to remove aAlbum, as it must always have a artist
    if (!this.equals(aAlbum.getArtist()))
    {
      albums.remove(aAlbum);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAlbumAt(Album aAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addAlbum(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAlbumAt(Album aAlbum, int index)
  {
    boolean wasAdded = false;
    if(albums.contains(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAlbumAt(aAlbum, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSongs()
  {
    return 0;
  }

  public Song addSong(String aTitle, int aDuration)
  {
    return new Song(aTitle, aDuration, this);
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Artist existingArtist = aSong.getArtist();
    boolean isNewArtist = existingArtist != null && !this.equals(existingArtist);
    if (isNewArtist)
    {
      aSong.setArtist(this);
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
    //Unable to remove aSong, as it must always have a artist
    if (!this.equals(aSong.getArtist()))
    {
      songs.remove(aSong);
      wasRemoved = true;
    }
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
    for(int i=albums.size(); i > 0; i--)
    {
      Album aAlbum = albums.get(i - 1);
      aAlbum.delete();
    }
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}