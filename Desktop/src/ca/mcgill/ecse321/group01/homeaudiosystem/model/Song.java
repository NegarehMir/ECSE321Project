/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;

// line 22 "../../../../../../HomeAudioSystem.ump"
// line 58 "../../../../../../HomeAudioSystem.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private int duration;

  //Song Associations
  private List<Artist> artists;
  private Album album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, int aDuration, Album aAlbum, Artist... allArtists)
  {
    title = aTitle;
    duration = aDuration;
    artists = new ArrayList<Artist>();
    boolean didAddArtists = setArtists(allArtists);
    if (!didAddArtists)
    {
      throw new RuntimeException("Unable to create Song, must have at least 1 artists");
    }
    boolean didAddAlbum = setAlbum(aAlbum);
    if (!didAddAlbum)
    {
      throw new RuntimeException("Unable to create song due to album");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public int getDuration()
  {
    return duration;
  }

  public Artist getArtist(int index)
  {
    Artist aArtist = artists.get(index);
    return aArtist;
  }

  public List<Artist> getArtists()
  {
    List<Artist> newArtists = Collections.unmodifiableList(artists);
    return newArtists;
  }

  public int numberOfArtists()
  {
    int number = artists.size();
    return number;
  }

  public boolean hasArtists()
  {
    boolean has = artists.size() > 0;
    return has;
  }

  public int indexOfArtist(Artist aArtist)
  {
    int index = artists.indexOf(aArtist);
    return index;
  }

  public Album getAlbum()
  {
    return album;
  }

  public boolean isNumberOfArtistsValid()
  {
    boolean isValid = numberOfArtists() >= minimumNumberOfArtists();
    return isValid;
  }

  public static int minimumNumberOfArtists()
  {
    return 1;
  }

  public boolean addArtist(Artist aArtist)
  {
    boolean wasAdded = false;
    if (artists.contains(aArtist)) { return false; }
    artists.add(aArtist);
    if (aArtist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aArtist.addSong(this);
      if (!wasAdded)
      {
        artists.remove(aArtist);
      }
    }
    return wasAdded;
  }

  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    if (!artists.contains(aArtist))
    {
      return wasRemoved;
    }

    if (numberOfArtists() <= minimumNumberOfArtists())
    {
      return wasRemoved;
    }

    int oldIndex = artists.indexOf(aArtist);
    artists.remove(oldIndex);
    if (aArtist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aArtist.removeSong(this);
      if (!wasRemoved)
      {
        artists.add(oldIndex,aArtist);
      }
    }
    return wasRemoved;
  }

  public boolean setArtists(Artist... newArtists)
  {
    boolean wasSet = false;
    ArrayList<Artist> verifiedArtists = new ArrayList<Artist>();
    for (Artist aArtist : newArtists)
    {
      if (verifiedArtists.contains(aArtist))
      {
        continue;
      }
      verifiedArtists.add(aArtist);
    }

    if (verifiedArtists.size() != newArtists.length || verifiedArtists.size() < minimumNumberOfArtists())
    {
      return wasSet;
    }

    ArrayList<Artist> oldArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for (Artist aNewArtist : verifiedArtists)
    {
      artists.add(aNewArtist);
      if (oldArtists.contains(aNewArtist))
      {
        oldArtists.remove(aNewArtist);
      }
      else
      {
        aNewArtist.addSong(this);
      }
    }

    for (Artist anOldArtist : oldArtists)
    {
      anOldArtist.removeSong(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addArtistAt(Artist aArtist, int index)
  {  
    boolean wasAdded = false;
    if(addArtist(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArtistAt(Artist aArtist, int index)
  {
    boolean wasAdded = false;
    if(artists.contains(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArtistAt(aArtist, index);
    }
    return wasAdded;
  }

  public boolean setAlbum(Album aAlbum)
  {
    boolean wasSet = false;
    if (aAlbum == null)
    {
      return wasSet;
    }

    Album existingAlbum = album;
    album = aAlbum;
    if (existingAlbum != null && !existingAlbum.equals(aAlbum))
    {
      existingAlbum.removeSong(this);
    }
    album.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Artist> copyOfArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for(Artist aArtist : copyOfArtists)
    {
      aArtist.removeSong(this);
    }
    Album placeholderAlbum = album;
    this.album = null;
    placeholderAlbum.removeSong(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null")
     + outputString;
  }
}