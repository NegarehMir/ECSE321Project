/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.sql.Date;

// line 27 "../../../../../../domainModel.umple"
// line 66 "../../../../../../domainModel.umple"
public class Album
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private String title;
  private Date releaseDate;
  private Genre genre;

  //Album Associations
  private AlbumTracklist albumTracklist;
  private HomeAudioSystem homeAudioSystem;
  private Artist artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, Date aReleaseDate, Genre aGenre, AlbumTracklist aAlbumTracklist, HomeAudioSystem aHomeAudioSystem, Artist aArtist)
  {
    title = aTitle;
    releaseDate = aReleaseDate;
    genre = aGenre;
    if (aAlbumTracklist == null || aAlbumTracklist.getAlbum() != null)
    {
      throw new RuntimeException("Unable to create Album due to aAlbumTracklist");
    }
    albumTracklist = aAlbumTracklist;
    boolean didAddHomeAudioSystem = setHomeAudioSystem(aHomeAudioSystem);
    if (!didAddHomeAudioSystem)
    {
      throw new RuntimeException("Unable to create album due to homeAudioSystem");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create album due to artist");
    }
  }

  public Album(String aTitle, Date aReleaseDate, Genre aGenre, String aNameForAlbumTracklist, HomeAudioSystem aHomeAudioSystemForAlbumTracklist, Song... allSongsForAlbumTracklist, HomeAudioSystem aHomeAudioSystem, Artist aArtist)
  {
    title = aTitle;
    releaseDate = aReleaseDate;
    genre = aGenre;
    albumTracklist = new AlbumTracklist(aNameForAlbumTracklist, aHomeAudioSystemForAlbumTracklist, this, allSongsForAlbumTracklist);
    boolean didAddHomeAudioSystem = setHomeAudioSystem(aHomeAudioSystem);
    if (!didAddHomeAudioSystem)
    {
      throw new RuntimeException("Unable to create album due to homeAudioSystem");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create album due to artist");
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

  public boolean setReleaseDate(Date aReleaseDate)
  {
    boolean wasSet = false;
    releaseDate = aReleaseDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setGenre(Genre aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public Date getReleaseDate()
  {
    return releaseDate;
  }

  public Genre getGenre()
  {
    return genre;
  }

  public AlbumTracklist getAlbumTracklist()
  {
    return albumTracklist;
  }

  public HomeAudioSystem getHomeAudioSystem()
  {
    return homeAudioSystem;
  }

  public Artist getArtist()
  {
    return artist;
  }

  public boolean setHomeAudioSystem(HomeAudioSystem aHomeAudioSystem)
  {
    boolean wasSet = false;
    if (aHomeAudioSystem == null)
    {
      return wasSet;
    }

    HomeAudioSystem existingHomeAudioSystem = homeAudioSystem;
    homeAudioSystem = aHomeAudioSystem;
    if (existingHomeAudioSystem != null && !existingHomeAudioSystem.equals(aHomeAudioSystem))
    {
      existingHomeAudioSystem.removeAlbum(this);
    }
    homeAudioSystem.addAlbum(this);
    wasSet = true;
    return wasSet;
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
    AlbumTracklist existingAlbumTracklist = albumTracklist;
    albumTracklist = null;
    if (existingAlbumTracklist != null)
    {
      existingAlbumTracklist.delete();
    }
    HomeAudioSystem placeholderHomeAudioSystem = homeAudioSystem;
    this.homeAudioSystem = null;
    placeholderHomeAudioSystem.removeAlbum(this);
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeAlbum(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "albumTracklist = "+(getAlbumTracklist()!=null?Integer.toHexString(System.identityHashCode(getAlbumTracklist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "homeAudioSystem = "+(getHomeAudioSystem()!=null?Integer.toHexString(System.identityHashCode(getHomeAudioSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null")
     + outputString;
  }
}