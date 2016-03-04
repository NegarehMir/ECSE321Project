<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class LocationMusicItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LocationMusicItem Associations
  private $playlists;
  private $songs;
  private $location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aLocation)
  {
    $this->playlists = array();
    $this->songs = array();
    $didAddLocation = $this->setLocation($aLocation);
    if (!$didAddLocation)
    {
      throw new Exception("Unable to create locationMusicItem due to location");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public function getSong_index($index)
  {
    $aSong = $this->songs[$index];
    return $aSong;
  }

  public function getSongs()
  {
    $newSongs = $this->songs;
    return $newSongs;
  }

  public function numberOfSongs()
  {
    $number = count($this->songs);
    return $number;
  }

  public function hasSongs()
  {
    $has = $this->numberOfSongs() > 0;
    return $has;
  }

  public function indexOfSong($aSong)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->songs as $song)
    {
      if ($song->equals($aSong))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getLocation()
  {
    return $this->location;
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
    $wasAdded = true;
    return $wasAdded;
  }

  public function removePlaylist($aPlaylist)
  {
    $wasRemoved = false;
    if ($this->indexOfPlaylist($aPlaylist) != -1)
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

  public static function minimumNumberOfSongs()
  {
    return 0;
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->songs[] = $aSong;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    if ($this->indexOfSong($aSong) != -1)
    {
      unset($this->songs[$this->indexOfSong($aSong)]);
      $this->songs = array_values($this->songs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSongAt($aSong, $index)
  {  
    $wasAdded = false;
    if($this->addSong($aSong))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSongs()) { $index = $this->numberOfSongs() - 1; }
      array_splice($this->songs, $this->indexOfSong($aSong), 1);
      array_splice($this->songs, $index, 0, array($aSong));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSongAt($aSong, $index)
  {
    $wasAdded = false;
    if($this->indexOfSong($aSong) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSongs()) { $index = $this->numberOfSongs() - 1; }
      array_splice($this->songs, $this->indexOfSong($aSong), 1);
      array_splice($this->songs, $index, 0, array($aSong));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSongAt($aSong, $index);
    }
    return $wasAdded;
  }

  public function setLocation($aLocation)
  {
    $wasSet = false;
    if ($aLocation == null)
    {
      return $wasSet;
    }
    
    $existingLocation = $this->location;
    $this->location = $aLocation;
    if ($existingLocation != null && $existingLocation != $aLocation)
    {
      $existingLocation->removeLocationMusicItem($this);
    }
    $this->location->addLocationMusicItem($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->playlists = array();
    $this->songs = array();
    $placeholderLocation = $this->location;
    $this->location = null;
    $placeholderLocation->removeLocationMusicItem($this);
  }

}
?>