<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private $name;
  private $volume;
  private $mute;

  //Location Associations
  private $songs;
  private $homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aVolume, $aMute, $aHomeAudioSystem)
  {
    $this->name = $aName;
    $this->volume = $aVolume;
    $this->mute = $aMute;
    $this->songs = array();
    $didAddHomeAudioSystem = $this->setHomeAudioSystem($aHomeAudioSystem);
    if (!$didAddHomeAudioSystem)
    {
      throw new Exception("Unable to create location due to homeAudioSystem");
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

  public function setVolume($aVolume)
  {
    $wasSet = false;
    $this->volume = $aVolume;
    $wasSet = true;
    return $wasSet;
  }

  public function setMute($aMute)
  {
    $wasSet = false;
    $this->mute = $aMute;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getVolume()
  {
    return $this->volume;
  }

  public function getMute()
  {
    return $this->mute;
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

  public static function minimumNumberOfSongs()
  {
    return 0;
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->songs[] = $aSong;
    if ($aSong->indexOfLocation($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aSong->addLocation($this);
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

    $oldIndex = $this->indexOfSong($aSong);
    unset($this->songs[$oldIndex]);
    if ($aSong->indexOfLocation($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aSong->removeLocation($this);
      if (!$wasRemoved)
      {
        $this->songs[$oldIndex] = $aSong;
        ksort($this->songs);
      }
    }
    $this->songs = array_values($this->songs);
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
      $existingHomeAudioSystem->removeLocation($this);
    }
    $this->homeAudioSystem->addLocation($this);
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
      $aSong->removeLocation($this);
    }
    $placeholderHomeAudioSystem = $this->homeAudioSystem;
    $this->homeAudioSystem = null;
    $placeholderHomeAudioSystem->removeLocation($this);
  }

}
?>