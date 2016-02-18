/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;

// line 21 "../../../../../../../../../ump/tmp943639/model.ump"
// line 59 "../../../../../../../../../ump/tmp943639/model.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private int duration;

  //Song Associations
  private List<Location> locations;
  private Artist artist;
  private List<Playlist> playlists;
  private AlbumTracklist albumTracklist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, int aDuration, Artist aArtist, AlbumTracklist aAlbumTracklist)
  {
    title = aTitle;
    duration = aDuration;
    locations = new ArrayList<Location>();
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create song due to artist");
    }
    playlists = new ArrayList<Playlist>();
    boolean didAddAlbumTracklist = setAlbumTracklist(aAlbumTracklist);
    if (!didAddAlbumTracklist)
    {
      throw new RuntimeException("Unable to create song due to albumTracklist");
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

  public Location getLocation(int index)
  {
    Location aLocation = locations.get(index);
    return aLocation;
  }

  public List<Location> getLocations()
  {
    List<Location> newLocations = Collections.unmodifiableList(locations);
    return newLocations;
  }

  public int numberOfLocations()
  {
    int number = locations.size();
    return number;
  }

  public boolean hasLocations()
  {
    boolean has = locations.size() > 0;
    return has;
  }

  public int indexOfLocation(Location aLocation)
  {
    int index = locations.indexOf(aLocation);
    return index;
  }

  public Artist getArtist()
  {
    return artist;
  }

  public Playlist getPlaylist(int index)
  {
    Playlist aPlaylist = playlists.get(index);
    return aPlaylist;
  }

  public List<Playlist> getPlaylists()
  {
    List<Playlist> newPlaylists = Collections.unmodifiableList(playlists);
    return newPlaylists;
  }

  public int numberOfPlaylists()
  {
    int number = playlists.size();
    return number;
  }

  public boolean hasPlaylists()
  {
    boolean has = playlists.size() > 0;
    return has;
  }

  public int indexOfPlaylist(Playlist aPlaylist)
  {
    int index = playlists.indexOf(aPlaylist);
    return index;
  }

  public AlbumTracklist getAlbumTracklist()
  {
    return albumTracklist;
  }

  public static int minimumNumberOfLocations()
  {
    return 0;
  }

  public boolean addLocation(Location aLocation)
  {
    boolean wasAdded = false;
    if (locations.contains(aLocation)) { return false; }
    locations.add(aLocation);
    if (aLocation.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLocation.addSong(this);
      if (!wasAdded)
      {
        locations.remove(aLocation);
      }
    }
    return wasAdded;
  }

  public boolean removeLocation(Location aLocation)
  {
    boolean wasRemoved = false;
    if (!locations.contains(aLocation))
    {
      return wasRemoved;
    }

    int oldIndex = locations.indexOf(aLocation);
    locations.remove(oldIndex);
    if (aLocation.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLocation.removeSong(this);
      if (!wasRemoved)
      {
        locations.add(oldIndex,aLocation);
      }
    }
    return wasRemoved;
  }

  public boolean addLocationAt(Location aLocation, int index)
  {  
    boolean wasAdded = false;
    if(addLocation(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocationAt(Location aLocation, int index)
  {
    boolean wasAdded = false;
    if(locations.contains(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocationAt(aLocation, index);
    }
    return wasAdded;
  }

  public boolean setArtist(Artist aArtist)
  {
    boolean wasSet = false;
    if (aArtist == null)
    {
      return wasSet;
    }

    Artist existingArtist = artist;
    artist = aArtist;
    if (existingArtist != null && !existingArtist.equals(aArtist))
    {
      existingArtist.removeSong(this);
    }
    artist.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfPlaylists()
  {
    return 0;
  }

  public boolean addPlaylist(Playlist aPlaylist)
  {
    boolean wasAdded = false;
    if (playlists.contains(aPlaylist)) { return false; }
    playlists.add(aPlaylist);
    if (aPlaylist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlaylist.addSong(this);
      if (!wasAdded)
      {
        playlists.remove(aPlaylist);
      }
    }
    return wasAdded;
  }

  public boolean removePlaylist(Playlist aPlaylist)
  {
    boolean wasRemoved = false;
    if (!playlists.contains(aPlaylist))
    {
      return wasRemoved;
    }

    int oldIndex = playlists.indexOf(aPlaylist);
    playlists.remove(oldIndex);
    if (aPlaylist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlaylist.removeSong(this);
      if (!wasRemoved)
      {
        playlists.add(oldIndex,aPlaylist);
      }
    }
    return wasRemoved;
  }

  public boolean addPlaylistAt(Playlist aPlaylist, int index)
  {  
    boolean wasAdded = false;
    if(addPlaylist(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlaylistAt(Playlist aPlaylist, int index)
  {
    boolean wasAdded = false;
    if(playlists.contains(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlaylistAt(aPlaylist, index);
    }
    return wasAdded;
  }

  public boolean setAlbumTracklist(AlbumTracklist aAlbumTracklist)
  {
    boolean wasSet = false;
    //Must provide albumTracklist to song
    if (aAlbumTracklist == null)
    {
      return wasSet;
    }

    if (albumTracklist != null && albumTracklist.numberOfSongs() <= AlbumTracklist.minimumNumberOfSongs())
    {
      return wasSet;
    }

    AlbumTracklist existingAlbumTracklist = albumTracklist;
    albumTracklist = aAlbumTracklist;
    if (existingAlbumTracklist != null && !existingAlbumTracklist.equals(aAlbumTracklist))
    {
      boolean didRemove = existingAlbumTracklist.removeSong(this);
      if (!didRemove)
      {
        albumTracklist = existingAlbumTracklist;
        return wasSet;
      }
    }
    albumTracklist.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Location> copyOfLocations = new ArrayList<Location>(locations);
    locations.clear();
    for(Location aLocation : copyOfLocations)
    {
      aLocation.removeSong(this);
    }
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeSong(this);
    ArrayList<Playlist> copyOfPlaylists = new ArrayList<Playlist>(playlists);
    playlists.clear();
    for(Playlist aPlaylist : copyOfPlaylists)
    {
      if (aPlaylist.numberOfSongs() <= Playlist.minimumNumberOfSongs())
      {
        aPlaylist.delete();
      }
      else
      {
        aPlaylist.removeSong(this);
      }
    }
    AlbumTracklist placeholderAlbumTracklist = albumTracklist;
    this.albumTracklist = null;
    placeholderAlbumTracklist.removeSong(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "albumTracklist = "+(getAlbumTracklist()!=null?Integer.toHexString(System.identityHashCode(getAlbumTracklist())):"null")
     + outputString;
  }
}