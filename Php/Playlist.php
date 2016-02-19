<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Playlist Attributes
  private $name;

  //Playlist Associations
  private $songs;
  private $homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $allSongs, $aHomeAudioSystem)
  {
    $this->name = $aName;
    $this->songs = array();
    $didAddSongs = $this->setSongs($allSongs);
    if (!$didAddSongs)
    {
      throw new Exception("Unable to create Playlist, must have at least 1 songs");
    }
    $didAddHomeAudioSystem = $this->setHomeAudioSystem($aHomeAudioSystem);
    if (!$didAddHomeAudioSystem)
    {
      throw new Exception("Unable to create playlist due to homeAudioSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
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

  public function getHomeAudioSystem()
  {
    return $this->homeAudioSystem;
  }

  public function isNumberOfSongsValid()
  {
    $isValid = $this->numberOfSongs() >= self::minimumNumberOfSongs();
    return $isValid;
  }

  public static function minimumNumberOfSongs()
  {
    return 1;
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->songs[] = $aSong;
    if ($aSong->indexOfPlaylist($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aSong->addPlaylist($this);
      if (!$wasAdded)
      {
        array_pop($this->songs);
      }
    }
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    if ($this->indexOfSong($aSong) == -1)
    {
      return $wasRemoved;
    }

    if ($this->numberOfSongs() <= self::minimumNumberOfSongs())
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfSong($aSong);
    unset($this->songs[$oldIndex]);
    if ($aSong->indexOfPlaylist($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aSong->removePlaylist($this);
      if (!$wasRemoved)
      {
        $this->songs[$oldIndex] = $aSong;
        ksort($this->songs);
      }
    }
    $this->songs = array_values($this->songs);
    return $wasRemoved;
  }

  public function setSongs($newSongs)
  {
    $wasSet = false;
    $verifiedSongs = array();
    foreach ($newSongs as $aSong)
    {
      if (array_search($aSong,$verifiedSongs) !== false)
      {
        continue;
      }
      $verifiedSongs[] = $aSong;
    }

    if (count($verifiedSongs) != count($newSongs) || count($verifiedSongs) < self::minimumNumberOfSongs())
    {
      return $wasSet;
    }

    $oldSongs = $this->songs;
    $this->songs = array();
    foreach ($verifiedSongs as $aNewSong)
    {
      $this->songs[] = $aNewSong;
      $removeIndex = array_search($aNewSong,$oldSongs);
      if ($removeIndex !== false)
      {
        unset($oldSongs[$removeIndex]);
        $oldSongs = array_values($oldSongs);
      }
      else
      {
        $aNewSong->addPlaylist($this);
      }
    }

    foreach ($oldSongs as $anOldSong)
    {
      $anOldSong->removePlaylist($this);
    }
    $wasSet = true;
    return $wasSet;
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

  public function setHomeAudioSystem($aHomeAudioSystem)
  {
    $wasSet = false;
    if ($aHomeAudioSystem == null)
    {
      return $wasSet;
    }
    
    $existingHomeAudioSystem = $this->homeAudioSystem;
    $this->homeAudioSystem = $aHomeAudioSystem;
    if ($existingHomeAudioSystem != null && $existingHomeAudioSystem != $aHomeAudioSystem)
    {
      $existingHomeAudioSystem->removePlaylist($this);
    }
    $this->homeAudioSystem->addPlaylist($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfSongs = $this->songs;
    $this->songs = array();
    foreach ($copyOfSongs as $aSong)
    {
      $aSong->removePlaylist($this);
    }
    $placeholderHomeAudioSystem = $this->homeAudioSystem;
    $this->homeAudioSystem = null;
    $placeholderHomeAudioSystem->removePlaylist($this);
  }

}
?>