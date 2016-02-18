<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

class HomeAudioSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HomeAudioSystem Associations
  private $locations;
  private $albums;
  private $artists;
  private $playlists;
  private $genres;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->locations = array();
    $this->albums = array();
    $this->artists = array();
    $this->playlists = array();
    $this->genres = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getLocation_index($index)
  {
    $aLocation = $this->locations[$index];
    return $aLocation;
  }

  public function getLocations()
  {
    $newLocations = $this->locations;
    return $newLocations;
  }

  public function numberOfLocations()
  {
    $number = count($this->locations);
    return $number;
  }

  public function hasLocations()
  {
    $has = $this->numberOfLocations() > 0;
    return $has;
  }

  public function indexOfLocation($aLocation)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->locations as $location)
    {
      if ($location->equals($aLocation))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAlbum_index($index)
  {
    $aAlbum = $this->albums[$index];
    return $aAlbum;
  }

  public function getAlbums()
  {
    $newAlbums = $this->albums;
    return $newAlbums;
  }

  public function numberOfAlbums()
  {
    $number = count($this->albums);
    return $number;
  }

  public function hasAlbums()
  {
    $has = $this->numberOfAlbums() > 0;
    return $has;
  }

  public function indexOfAlbum($aAlbum)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->albums as $album)
    {
      if ($album->equals($aAlbum))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getArtist_index($index)
  {
    $aArtist = $this->artists[$index];
    return $aArtist;
  }

  public function getArtists()
  {
    $newArtists = $this->artists;
    return $newArtists;
  }

  public function numberOfArtists()
  {
    $number = count($this->artists);
    return $number;
  }

  public function hasArtists()
  {
    $has = $this->numberOfArtists() > 0;
    return $has;
  }

  public function indexOfArtist($aArtist)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->artists as $artist)
    {
      if ($artist->equals($aArtist))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getPlaylist_index($index)
  {
    $aPlaylist = $this->playlists[$index];
    return $aPlaylist;
  }

  public function getPlaylists()
  {
    $newPlaylists = $this->playlists;
    return $newPlaylists;
  }

  public function numberOfPlaylists()
  {
    $number = count($this->playlists);
    return $number;
  }

  public function hasPlaylists()
  {
    $has = $this->numberOfPlaylists() > 0;
    return $has;
  }

  public function indexOfPlaylist($aPlaylist)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->playlists as $playlist)
    {
      if ($playlist->equals($aPlaylist))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getGenre_index($index)
  {
    $aGenre = $this->genres[$index];
    return $aGenre;
  }

  public function getGenres()
  {
    $newGenres = $this->genres;
    return $newGenres;
  }

  public function numberOfGenres()
  {
    $number = count($this->genres);
    return $number;
  }

  public function hasGenres()
  {
    $has = $this->numberOfGenres() > 0;
    return $has;
  }

  public function indexOfGenre($aGenre)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->genres as $genre)
    {
      if ($genre->equals($aGenre))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfLocations()
  {
    return 0;
  }

  public function addLocationVia($aName, $aVolume, $aMute)
  {
    return new Location($aName, $aVolume, $aMute, $this);
  }

  public function addLocation($aLocation)
  {
    $wasAdded = false;
    if ($this->indexOfLocation($aLocation) !== -1) { return false; }
    $existingHomeAudioSystem = $aLocation->getHomeAudioSystem();
    $isNewHomeAudioSystem = $existingHomeAudioSystem != null && $this !== $existingHomeAudioSystem;
    if ($isNewHomeAudioSystem)
    {
      $aLocation->setHomeAudioSystem($this);
    }
    else
    {
      $this->locations[] = $aLocation;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeLocation($aLocation)
  {
    $wasRemoved = false;
    //Unable to remove aLocation, as it must always have a homeAudioSystem
    if ($this !== $aLocation->getHomeAudioSystem())
    {
      unset($this->locations[$this->indexOfLocation($aLocation)]);
      $this->locations = array_values($this->locations);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addLocationAt($aLocation, $index)
  {  
    $wasAdded = false;
    if($this->addLocation($aLocation))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocations()) { $index = $this->numberOfLocations() - 1; }
      array_splice($this->locations, $this->indexOfLocation($aLocation), 1);
      array_splice($this->locations, $index, 0, array($aLocation));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveLocationAt($aLocation, $index)
  {
    $wasAdded = false;
    if($this->indexOfLocation($aLocation) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLocations()) { $index = $this->numberOfLocations() - 1; }
      array_splice($this->locations, $this->indexOfLocation($aLocation), 1);
      array_splice($this->locations, $index, 0, array($aLocation));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addLocationAt($aLocation, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAlbums()
  {
    return 0;
  }

  public function addAlbumVia($aTitle, $aReleaseDate, $aGenre, $aAlbumTracklist, $aArtist)
  {
    return new Album($aTitle, $aReleaseDate, $aGenre, $aAlbumTracklist, $this, $aArtist);
  }

  public function addAlbum($aAlbum)
  {
    $wasAdded = false;
    if ($this->indexOfAlbum($aAlbum) !== -1) { return false; }
    $existingHomeAudioSystem = $aAlbum->getHomeAudioSystem();
    $isNewHomeAudioSystem = $existingHomeAudioSystem != null && $this !== $existingHomeAudioSystem;
    if ($isNewHomeAudioSystem)
    {
      $aAlbum->setHomeAudioSystem($this);
    }
    else
    {
      $this->albums[] = $aAlbum;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAlbum($aAlbum)
  {
    $wasRemoved = false;
    //Unable to remove aAlbum, as it must always have a homeAudioSystem
    if ($this !== $aAlbum->getHomeAudioSystem())
    {
      unset($this->albums[$this->indexOfAlbum($aAlbum)]);
      $this->albums = array_values($this->albums);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAlbumAt($aAlbum, $index)
  {  
    $wasAdded = false;
    if($this->addAlbum($aAlbum))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAlbumAt($aAlbum, $index)
  {
    $wasAdded = false;
    if($this->indexOfAlbum($aAlbum) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAlbums()) { $index = $this->numberOfAlbums() - 1; }
      array_splice($this->albums, $this->indexOfAlbum($aAlbum), 1);
      array_splice($this->albums, $index, 0, array($aAlbum));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAlbumAt($aAlbum, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfArtists()
  {
    return 0;
  }

  public function addArtistVia($aTitle)
  {
    return new Artist($aTitle, $this);
  }

  public function addArtist($aArtist)
  {
    $wasAdded = false;
    if ($this->indexOfArtist($aArtist) !== -1) { return false; }
    $existingHomeAudioSystem = $aArtist->getHomeAudioSystem();
    $isNewHomeAudioSystem = $existingHomeAudioSystem != null && $this !== $existingHomeAudioSystem;
    if ($isNewHomeAudioSystem)
    {
      $aArtist->setHomeAudioSystem($this);
    }
    else
    {
      $this->artists[] = $aArtist;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeArtist($aArtist)
  {
    $wasRemoved = false;
    //Unable to remove aArtist, as it must always have a homeAudioSystem
    if ($this !== $aArtist->getHomeAudioSystem())
    {
      unset($this->artists[$this->indexOfArtist($aArtist)]);
      $this->artists = array_values($this->artists);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addArtistAt($aArtist, $index)
  {  
    $wasAdded = false;
    if($this->addArtist($aArtist))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArtists()) { $index = $this->numberOfArtists() - 1; }
      array_splice($this->artists, $this->indexOfArtist($aArtist), 1);
      array_splice($this->artists, $index, 0, array($aArtist));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveArtistAt($aArtist, $index)
  {
    $wasAdded = false;
    if($this->indexOfArtist($aArtist) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArtists()) { $index = $this->numberOfArtists() - 1; }
      array_splice($this->artists, $this->indexOfArtist($aArtist), 1);
      array_splice($this->artists, $index, 0, array($aArtist));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addArtistAt($aArtist, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfPlaylists()
  {
    return 0;
  }

  public function addPlaylistVia($aName, $allSongs)
  {
    return new Playlist($aName, $allSongs, $this);
  }

  public function addPlaylist($aPlaylist)
  {
    $wasAdded = false;
    if ($this->indexOfPlaylist($aPlaylist) !== -1) { return false; }
    $existingHomeAudioSystem = $aPlaylist->getHomeAudioSystem();
    $isNewHomeAudioSystem = $existingHomeAudioSystem != null && $this !== $existingHomeAudioSystem;
    if ($isNewHomeAudioSystem)
    {
      $aPlaylist->setHomeAudioSystem($this);
    }
    else
    {
      $this->playlists[] = $aPlaylist;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removePlaylist($aPlaylist)
  {
    $wasRemoved = false;
    //Unable to remove aPlaylist, as it must always have a homeAudioSystem
    if ($this !== $aPlaylist->getHomeAudioSystem())
    {
      unset($this->playlists[$this->indexOfPlaylist($aPlaylist)]);
      $this->playlists = array_values($this->playlists);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addPlaylistAt($aPlaylist, $index)
  {  
    $wasAdded = false;
    if($this->addPlaylist($aPlaylist))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylists()) { $index = $this->numberOfPlaylists() - 1; }
      array_splice($this->playlists, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlists, $index, 0, array($aPlaylist));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMovePlaylistAt($aPlaylist, $index)
  {
    $wasAdded = false;
    if($this->indexOfPlaylist($aPlaylist) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPlaylists()) { $index = $this->numberOfPlaylists() - 1; }
      array_splice($this->playlists, $this->indexOfPlaylist($aPlaylist), 1);
      array_splice($this->playlists, $index, 0, array($aPlaylist));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addPlaylistAt($aPlaylist, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfGenres()
  {
    return 0;
  }

  public function addGenreVia($aName)
  {
    return new Genre($aName, $this);
  }

  public function addGenre($aGenre)
  {
    $wasAdded = false;
    if ($this->indexOfGenre($aGenre) !== -1) { return false; }
    $existingHomeAudioSystem = $aGenre->getHomeAudioSystem();
    $isNewHomeAudioSystem = $existingHomeAudioSystem != null && $this !== $existingHomeAudioSystem;
    if ($isNewHomeAudioSystem)
    {
      $aGenre->setHomeAudioSystem($this);
    }
    else
    {
      $this->genres[] = $aGenre;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeGenre($aGenre)
  {
    $wasRemoved = false;
    //Unable to remove aGenre, as it must always have a homeAudioSystem
    if ($this !== $aGenre->getHomeAudioSystem())
    {
      unset($this->genres[$this->indexOfGenre($aGenre)]);
      $this->genres = array_values($this->genres);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addGenreAt($aGenre, $index)
  {  
    $wasAdded = false;
    if($this->addGenre($aGenre))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfGenres()) { $index = $this->numberOfGenres() - 1; }
      array_splice($this->genres, $this->indexOfGenre($aGenre), 1);
      array_splice($this->genres, $index, 0, array($aGenre));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveGenreAt($aGenre, $index)
  {
    $wasAdded = false;
    if($this->indexOfGenre($aGenre) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfGenres()) { $index = $this->numberOfGenres() - 1; }
      array_splice($this->genres, $this->indexOfGenre($aGenre), 1);
      array_splice($this->genres, $index, 0, array($aGenre));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addGenreAt($aGenre, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->locations) > 0)
    {
      $aLocation = $this->locations[count($this->locations) - 1];
      $aLocation->delete();
      unset($this->locations[$this->indexOfLocation($aLocation)]);
      $this->locations = array_values($this->locations);
    }
    
      
    while (count($this->albums) > 0)
    {
      $aAlbum = $this->albums[count($this->albums) - 1];
      $aAlbum->delete();
      unset($this->albums[$this->indexOfAlbum($aAlbum)]);
      $this->albums = array_values($this->albums);
    }
    
      
    while (count($this->artists) > 0)
    {
      $aArtist = $this->artists[count($this->artists) - 1];
      $aArtist->delete();
      unset($this->artists[$this->indexOfArtist($aArtist)]);
      $this->artists = array_values($this->artists);
    }
    
      
    while (count($this->playlists) > 0)
    {
      $aPlaylist = $this->playlists[count($this->playlists) - 1];
      $aPlaylist->delete();
      unset($this->playlists[$this->indexOfPlaylist($aPlaylist)]);
      $this->playlists = array_values($this->playlists);
    }
    
      
    while (count($this->genres) > 0)
    {
      $aGenre = $this->genres[count($this->genres) - 1];
      $aGenre->delete();
      unset($this->genres[$this->indexOfGenre($aGenre)]);
      $this->genres = array_values($this->genres);
    }
    
      
  }

}
?>