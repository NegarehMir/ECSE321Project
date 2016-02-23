/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f5592a4 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.sql.Date;

// line 28 "../../../../../../../../../ump/tmp960453/model.ump"
// line 69 "../../../../../../../../../ump/tmp960453/model.ump"
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
  private Artist artist;
  private AlbumTracklist albumTracklist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, Date aReleaseDate, Genre aGenre, Artist aArtist, AlbumTracklist aAlbumTracklist)
  {
    title = aTitle;
    releaseDate = aReleaseDate;
    genre = aGenre;
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create album due to artist");
    }
    if (!setAlbumTracklist(aAlbumTracklist))
    {
      throw new RuntimeException("Unable to create Album due to aAlbumTracklist");
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

  public Artist getArtist()
  {
    return artist;
  }

  public AlbumTracklist getAlbumTracklist()
  {
    return albumTracklist;
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

  public boolean setAlbumTracklist(AlbumTracklist aNewAlbumTracklist)
  {
    boolean wasSet = false;
    if (aNewAlbumTracklist != null)
    {
      albumTracklist = aNewAlbumTracklist;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeAlbum(this);
    albumTracklist = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "albumTracklist = "+(getAlbumTracklist()!=null?Integer.toHexString(System.identityHashCode(getAlbumTracklist())):"null")
     + outputString;
  }
}