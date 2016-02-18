<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private $title;
  private $duration;

  //Song Associations
  private $locations;
  private $artist;
  private $playlists;
  private $albumTracklist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aDuration, $aArtist, $aAlbumTracklist)
  {
    $this->title = $aTitle;
    $this->duration = $aDuration;
    $this->locations = array();
    $didAddArtist = $this->setArtist($aArtist);
    if (!$didAddArtist)
    {
      throw new Exception("Unable to create song due to artist");
    }
    $this->playlists = array();
    $didAddAlbumTracklist = $this->setAlbumTracklist($aAlbumTracklist);
    if (!$didAddAlbumTracklist)
    {
      throw new Exception("Unable to create song due to albumTracklist");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTitle($aTitle)
  {
    $wasSet = false;
    $this->title = $aTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setDuration($aDuration)
  {
    $wasSet = false;
    $this->duration = $aDuration;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getDuration()
  {
    return $this->duration;
  }

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

  public function getArtist()
  {
    return $this->artist;
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

  public function getAlbumTracklist()
  {
    return $this->albumTracklist;
  }

  public static function minimumNumberOfLocations()
  {
    return 0;
  }

  public function addLocation($aLocation)
  {
    $wasAdded = false;
    if ($this->indexOfLocation($aLocation) !== -1) { return false; }
    $this->locations[] = $aLocation;
    if ($aLocation->indexOfSong($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aLocation->addSong($this);
      if (!$wasAdded)
      {
        array_pop($this->locations);
      }
    }
    return $wasAdded;
  }

  public function removeLocation($aLocation)
  {
    $wasRemoved = false;
    if ($this->indexOfLocation($aLocation) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfLocation($aLocation);
    unset($this->locations[$oldIndex]);
    if ($aLocation->indexOfSong($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aLocation->removeSong($this);
      if (!$wasRemoved)
      {
        $this->locations[$oldIndex] = $aLocation;
        ksort($this->locations);
      }
    }
    $this->locations = array_values($this->locations);
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

  public function setArtist($aArtist)
  {
    $wasSet = false;
    if ($aArtist == null)
    {
      return $wasSet;
    }
    
    $existingArtist = $this->artist;
    $this->artist = $aArtist;
    if ($existingArtist != null && $existingArtist != $aArtist)
    {
      $existingArtist->removeSong($this);
    }
    $this->artist->addSong($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfPlaylists()
  {
    return 0;
  }

  public function addPlaylist($aPlaylist)
  {
    $wasAdded = false;
    if ($this->indexOfPlaylist($aPlaylist) !== -1) { return false; }
    $this->playlists[] = $aPlaylist;
    if ($aPlaylist->indexOfSong($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aPlaylist->addSong($this);
      if (!$wasAdded)
      {
        array_pop($this->playlists);
      }
    }
    return $wasAdded;
  }

  public function removePlaylist($aPlaylist)
  {
    $wasRemoved = false;
    if ($this->indexOfPlaylist($aPlaylist) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfPlaylist($aPlaylist);
    unset($this->playlists[$oldIndex]);
    if ($aPlaylist->indexOfSong($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aPlaylist->removeSong($this);
      if (!$wasRemoved)
      {
        $this->playlists[$oldIndex] = $aPlaylist;
        ksort($this->playlists);
      }
    }
    $this->playlists = array_values($this->playlists);
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

  public function setAlbumTracklist($aAlbumTracklist)
  {
    $wasSet = false;
    //Must provide albumTracklist to song
    if ($aAlbumTracklist == null)
    {
      return $wasSet;
    }

    if ($this->albumTracklist != null && $this->albumTracklist->numberOfSongs() <= AlbumTracklist::minimumNumberOfSongs())
    {
      return $wasSet;
    }

    $existingAlbumTracklist = $this->albumTracklist;
    $this->albumTracklist = $aAlbumTracklist;
    if ($existingAlbumTracklist != null && $existingAlbumTracklist != $aAlbumTracklist)
    {
      $didRemove = $existingAlbumTracklist->removeSong($this);
      if (!$didRemove)
      {
        $this->albumTracklist = $existingAlbumTracklist;
        return $wasSet;
      }
    }
    $this->albumTracklist->addSong($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfLocations = $this->locations;
    $this->locations = array();
    foreach ($copyOfLocations as $aLocation)
    {
      $aLocation->removeSong($this);
    }
    $placeholderArtist = $this->artist;
    $this->artist = null;
    $placeholderArtist->removeSong($this);
    $copyOfPlaylists = $this->playlists;
    $this->playlists = array();
    foreach ($copyOfPlaylists as $aPlaylist)
    {
      if ($aPlaylist->numberOfSongs() <= Playlist::minimumNumberOfSongs())
      {
        $aPlaylist->delete();
      }
      else
      {
        $aPlaylist->removeSong($this);
      }
    }
    $placeholderAlbumTracklist = $this->albumTracklist;
    $this->albumTracklist = null;
    $placeholderAlbumTracklist->removeSong($this);
  }

}
?>