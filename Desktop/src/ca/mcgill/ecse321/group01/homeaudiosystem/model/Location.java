/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;

// line 14 "../../../../../../HomeAudioSystem.ump"
// line 85 "../../../../../../HomeAudioSystem.ump"
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
  private List<LocationMusicItem> locationMusicItems;
  private HomeAudioSystem homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(String aName, int aVolume, boolean aMute, HomeAudioSystem aHomeAudioSystem)
  {
    name = aName;
    volume = aVolume;
    mute = aMute;
    locationMusicItems = new ArrayList<LocationMusicItem>();
    boolean didAddHomeAudioSystem = setHomeAudioSystem(aHomeAudioSystem);
    if (!didAddHomeAudioSystem)
    {
      throw new RuntimeException("Unable to create location due to homeAudioSystem");
    }
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

  public LocationMusicItem getLocationMusicItem(int index)
  {
    LocationMusicItem aLocationMusicItem = locationMusicItems.get(index);
    return aLocationMusicItem;
  }

  public List<LocationMusicItem> getLocationMusicItems()
  {
    List<LocationMusicItem> newLocationMusicItems = Collections.unmodifiableList(locationMusicItems);
    return newLocationMusicItems;
  }

  public int numberOfLocationMusicItems()
  {
    int number = locationMusicItems.size();
    return number;
  }

  public boolean hasLocationMusicItems()
  {
    boolean has = locationMusicItems.size() > 0;
    return has;
  }

  public int indexOfLocationMusicItem(LocationMusicItem aLocationMusicItem)
  {
    int index = locationMusicItems.indexOf(aLocationMusicItem);
    return index;
  }

  public HomeAudioSystem getHomeAudioSystem()
  {
    return homeAudioSystem;
  }

  public static int minimumNumberOfLocationMusicItems()
  {
    return 0;
  }

  public boolean addLocationMusicItem(LocationMusicItem aLocationMusicItem)
  {
    boolean wasAdded = false;
    if (locationMusicItems.contains(aLocationMusicItem)) { return false; }
    locationMusicItems.add(aLocationMusicItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLocationMusicItem(LocationMusicItem aLocationMusicItem)
  {
    boolean wasRemoved = false;
    if (locationMusicItems.contains(aLocationMusicItem))
    {
      locationMusicItems.remove(aLocationMusicItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLocationMusicItemAt(LocationMusicItem aLocationMusicItem, int index)
  {  
    boolean wasAdded = false;
    if(addLocationMusicItem(aLocationMusicItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocationMusicItems()) { index = numberOfLocationMusicItems() - 1; }
      locationMusicItems.remove(aLocationMusicItem);
      locationMusicItems.add(index, aLocationMusicItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocationMusicItemAt(LocationMusicItem aLocationMusicItem, int index)
  {
    boolean wasAdded = false;
    if(locationMusicItems.contains(aLocationMusicItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocationMusicItems()) { index = numberOfLocationMusicItems() - 1; }
      locationMusicItems.remove(aLocationMusicItem);
      locationMusicItems.add(index, aLocationMusicItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocationMusicItemAt(aLocationMusicItem, index);
    }
    return wasAdded;
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
      existingHomeAudioSystem.removeLocation(this);
    }
    homeAudioSystem.addLocation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    locationMusicItems.clear();
    HomeAudioSystem placeholderHomeAudioSystem = homeAudioSystem;
    this.homeAudioSystem = null;
    placeholderHomeAudioSystem.removeLocation(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "volume" + ":" + getVolume()+ "," +
            "mute" + ":" + getMute()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "homeAudioSystem = "+(getHomeAudioSystem()!=null?Integer.toHexString(System.identityHashCode(getHomeAudioSystem())):"null")
     + outputString;
  }
}