/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../../JavaHomeAudioSystem.ump"
// line 48 "../../../../../../HomeAudioSystem.ump"
// line 69 "../../../../../../HomeAudioSystem.ump"
public class Album extends Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private Date releaseDate;

  //Album Associations
  private List<Song> songs;
  private Artist artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, HomeAudioSystem aHomeAudioSystem, Date aReleaseDate, Artist aArtist)
  {
    super(aTitle, aHomeAudioSystem);
    releaseDate = aReleaseDate;
    songs = new ArrayList<Song>();
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create album due to artist");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReleaseDate(Date aReleaseDate)
  {
    boolean wasSet = false;
    releaseDate = aReleaseDate;
    wasSet = true;
    return wasSet;
  }

  public Date getReleaseDate()
  {
    return releaseDate;
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

  public Artist getArtist()
  {
    return artist;
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

  public Song addSong(String aTitle, int aDuration)
  {
    Song aNewSong = new Song(aTitle, aDuration, this);
    return aNewSong;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Album existingAlbum = aSong.getAlbum();
    boolean isNewAlbum = existingAlbum != null && !this.equals(existingAlbum);

    if (isNewAlbum && existingAlbum.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    if (isNewAlbum)
    {
      aSong.setAlbum(this);
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
    //Unable to remove aSong, as it must always have a album
    if (this.equals(aSong.getAlbum()))
    {
      return wasRemoved;
    }

    //album already at minimum (1)
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
      existingArtist.removeAlbum(this);
    }
    artist.addAlbum(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (songs.size() > 0)
    {
      Song aSong = songs.get(songs.size() - 1);
      aSong.delete();
      songs.remove(aSong);
    }
    
      
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeAlbum(this);
    super.delete();
  }

  // line 9 "../../../../../../JavaHomeAudioSystem.ump"
   public void setGenre(Genres aGenre){
    genre = aGenre;
  }

  // line 13 "../../../../../../JavaHomeAudioSystem.ump"
   public Genres getGenre(){
    return genre;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 4 ../../../../../../JavaHomeAudioSystem.ump
  public enum Genres {Alternative, Classical, Country, Electronic, Rap, Pop, Rock, Jazz};
// line 6 ../../../../../../JavaHomeAudioSystem.ump
  private Genres genre ;

  
}