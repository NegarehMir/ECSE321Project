/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;

// line 47 "../../../../../../../../../ump/tmp943639/model.ump"
// line 88 "../../../../../../../../../ump/tmp943639/model.ump"
public class Genre
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Genre Attributes
  private String name;

  //Genre Associations
  private HomeAudioSystem homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Genre(String aName, HomeAudioSystem aHomeAudioSystem)
  {
    name = aName;
    boolean didAddHomeAudioSystem = setHomeAudioSystem(aHomeAudioSystem);
    if (!didAddHomeAudioSystem)
    {
      throw new RuntimeException("Unable to create genre due to homeAudioSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

  public HomeAudioSystem getHomeAudioSystem()
  {
    return homeAudioSystem;
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
      existingHomeAudioSystem.removeGenre(this);
    }
    homeAudioSystem.addGenre(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    HomeAudioSystem placeholderHomeAudioSystem = homeAudioSystem;
    this.homeAudioSystem = null;
    placeholderHomeAudioSystem.removeGenre(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "homeAudioSystem = "+(getHomeAudioSystem()!=null?Integer.toHexString(System.identityHashCode(getHomeAudioSystem())):"null")
     + outputString;
  }
}