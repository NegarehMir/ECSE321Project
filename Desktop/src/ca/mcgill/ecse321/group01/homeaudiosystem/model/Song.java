/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-5871cbd modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;

// line 22 "../../../../../../../../../ump/tmp960453/model.ump"
// line 62 "../../../../../../../../../ump/tmp960453/model.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private int duration;

  //Song Associations
  private Artist artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, int aDuration, Artist aArtist)
  {
    title = aTitle;
    duration = aDuration;
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create song due to artist");
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

  public Artist getArtist()
  {
    return artist;
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

  public void delete()
  {
    Artist placeholderArtist = artist;
    this.artist = null;
    placeholderArtist.removeSong(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null")
     + outputString;
  }
}