/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;
import java.util.*;
import java.sql.Date;

// line 4 "../../../../../../../../../ump/tmp943639/model.ump"
// line 105 "../../../../../../../../../ump/tmp943639/model.ump"
public class HomeAudioSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HomeAudioSystem Associations
  private List<Location> locations;
  private List<Album> albums;
  private List<Artist> artists;
  private List<Playlist> playlists;
  private List<Genre> genres;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HomeAudioSystem()
  {
    locations = new ArrayList<Location>();
    albums = new ArrayList<Album>();
    artists = new ArrayList<Artist>();
    playlists = new ArrayList<Playlist>();
    genres = new ArrayList<Genre>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Genre getGenre(int index)
  {
    Genre aGenre = genres.get(index);
    return aGenre;
  }

  public List<Genre> getGenres()
  {
    List<Genre> newGenres = Collections.unmodifiableList(genres);
    return newGenres;
  }

  public int numberOfGenres()
  {
    int number = genres.size();
    return number;
  }

  public boolean hasGenres()
  {
    boolean has = genres.size() > 0;
    return has;
  }

  public int indexOfGenre(Genre aGenre)
  {
    int index = genres.indexOf(aGenre);
    return index;
  }

  public static int minimumNumberOfLocations()
  {
    return 0;
  }

  public Location addLocation(String aName, int aVolume, boolean aMute)
  {
    return new Location(aName, aVolume, aMute, this);
  }

  public boolean addLocation(Location aLocation)
  {
    boolean wasAdded = false;
    if (locations.contains(aLocation)) { return false; }
    HomeAudioSystem existingHomeAudioSystem = aLocation.getHomeAudioSystem();
    boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
    if (isNewHomeAudioSystem)
    {
      aLocation.setHomeAudioSystem(this);
    }
    else
    {
      locations.add(aLocation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLocation(Location aLocation)
  {
    boolean wasRemoved = false;
    //Unable to remove aLocation, as it must always have a homeAudioSystem
    if (!this.equals(aLocation.getHomeAudioSystem()))
    {
      locations.remove(aLocation);
      wasRemoved = true;
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

  public static int minimumNumberOfAlbums()
  {
    return 0;
  }

  public Album addAlbum(String aTitle, Date aReleaseDate, Genre aGenre, AlbumTracklist aAlbumTracklist, Artist aArtist)
  {
    return new Album(aTitle, aReleaseDate, aGenre, aAlbumTracklist, this, aArtist);
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    HomeAudioSystem existingHomeAudioSystem = aAlbum.getHomeAudioSystem();
    boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
    if (isNewHomeAudioSystem)
    {
      aAlbum.setHomeAudioSystem(this);
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
    //Unable to remove aAlbum, as it must always have a homeAudioSystem
    if (!this.equals(aAlbum.getHomeAudioSystem()))
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

  public static int minimumNumberOfArtists()
  {
    return 0;
  }

  public Artist addArtist(String aTitle)
  {
    return new Artist(aTitle, this);
  }

  public boolean addArtist(Artist aArtist)
  {
    boolean wasAdded = false;
    if (artists.contains(aArtist)) { return false; }
    HomeAudioSystem existingHomeAudioSystem = aArtist.getHomeAudioSystem();
    boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
    if (isNewHomeAudioSystem)
    {
      aArtist.setHomeAudioSystem(this);
    }
    else
    {
      artists.add(aArtist);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    //Unable to remove aArtist, as it must always have a homeAudioSystem
    if (!this.equals(aArtist.getHomeAudioSystem()))
    {
      artists.remove(aArtist);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public static int minimumNumberOfPlaylists()
  {
    return 0;
  }

  public Playlist addPlaylist(String aName, Song... allSongs)
  {
    return new Playlist(aName, this, allSongs);
  }

  public boolean addPlaylist(Playlist aPlaylist)
  {
    boolean wasAdded = false;
    if (playlists.contains(aPlaylist)) { return false; }
    HomeAudioSystem existingHomeAudioSystem = aPlaylist.getHomeAudioSystem();
    boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
    if (isNewHomeAudioSystem)
    {
      aPlaylist.setHomeAudioSystem(this);
    }
    else
    {
      playlists.add(aPlaylist);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlaylist(Playlist aPlaylist)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlaylist, as it must always have a homeAudioSystem
    if (!this.equals(aPlaylist.getHomeAudioSystem()))
    {
      playlists.remove(aPlaylist);
      wasRemoved = true;
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

  public static int minimumNumberOfGenres()
  {
    return 0;
  }

  public Genre addGenre(String aName)
  {
    return new Genre(aName, this);
  }

  public boolean addGenre(Genre aGenre)
  {
    boolean wasAdded = false;
    if (genres.contains(aGenre)) { return false; }
    HomeAudioSystem existingHomeAudioSystem = aGenre.getHomeAudioSystem();
    boolean isNewHomeAudioSystem = existingHomeAudioSystem != null && !this.equals(existingHomeAudioSystem);
    if (isNewHomeAudioSystem)
    {
      aGenre.setHomeAudioSystem(this);
    }
    else
    {
      genres.add(aGenre);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGenre(Genre aGenre)
  {
    boolean wasRemoved = false;
    //Unable to remove aGenre, as it must always have a homeAudioSystem
    if (!this.equals(aGenre.getHomeAudioSystem()))
    {
      genres.remove(aGenre);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGenreAt(Genre aGenre, int index)
  {  
    boolean wasAdded = false;
    if(addGenre(aGenre))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGenres()) { index = numberOfGenres() - 1; }
      genres.remove(aGenre);
      genres.add(index, aGenre);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGenreAt(Genre aGenre, int index)
  {
    boolean wasAdded = false;
    if(genres.contains(aGenre))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGenres()) { index = numberOfGenres() - 1; }
      genres.remove(aGenre);
      genres.add(index, aGenre);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGenreAt(aGenre, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (locations.size() > 0)
    {
      Location aLocation = locations.get(locations.size() - 1);
      aLocation.delete();
      locations.remove(aLocation);
    }
    
      
    while (albums.size() > 0)
    {
      Album aAlbum = albums.get(albums.size() - 1);
      aAlbum.delete();
      albums.remove(aAlbum);
    }
    
      
    while (artists.size() > 0)
    {
      Artist aArtist = artists.get(artists.size() - 1);
      aArtist.delete();
      artists.remove(aArtist);
    }
    
      
    while (playlists.size() > 0)
    {
      Playlist aPlaylist = playlists.get(playlists.size() - 1);
      aPlaylist.delete();
      playlists.remove(aPlaylist);
    }
    
      
    while (genres.size() > 0)
    {
      Genre aGenre = genres.get(genres.size() - 1);
      aGenre.delete();
      genres.remove(aGenre);
    }
    
      
  }

}