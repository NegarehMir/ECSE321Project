/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-2f66a7f modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;

// line 14 "../../../../../../../../../ump/tmp960453/model.ump"
// line 97 "../../../../../../../../../ump/tmp960453/model.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private String name;
  private int volume;
  private boolean mute;

  //Location Associations
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(String aName, int aVolume, boolean aMute)
  {
    name = aName;
    volume = aVolume;
    mute = aMute;
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

  public boolean setVolume(int aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public boolean setMute(boolean aMute)
  {
    boolean wasSet = false;
    mute = aMute;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getVolume()
  {
    return volume;
  }

  public boolean getMute()
  {
    return mute;
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

  public static int minimumNumberOfSongs()
  {
    return 0;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    songs.add(aSong);
    if (aSong.indexOfLocation(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSong.addLocation(this);
      if (!wasAdded)
      {
        songs.remove(aSong);
      }
    }
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (!songs.contains(aSong))
    {
      return wasRemoved;
    }

    int oldIndex = songs.indexOf(aSong);
    songs.remove(oldIndex);
    if (aSong.indexOfLocation(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSong.removeLocation(this);
      if (!wasRemoved)
      {
        songs.add(oldIndex,aSong);
      }
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
    ArrayList<Song> copyOfSongs = new ArrayList<Song>(songs);
    songs.clear();
    for(Song aSong : copyOfSongs)
    {
      aSong.removeLocation(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "volume" + ":" + getVolume()+ "," +
            "mute" + ":" + getMute()+ "]"
     + outputString;
  }
}