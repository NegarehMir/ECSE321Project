<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class AlbumTracklist extends Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AlbumTracklist Associations
  private $songs;
  private $album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName = null, $allSongs = null, $aHomeAudioSystem = null, $aAlbum = null)
  {
    if (func_num_args() == 0) { return; }

    parent::__construct($aName, $allSongs, $aHomeAudioSystem);
    $this->songs = array();
    if ($aAlbum == null || $aAlbum->getAlbumTracklist() != null)
    {
      throw new Exception("Unable to create AlbumTracklist due to aAlbum");
    }
    $this->album = $aAlbum;
  }
  public static function newInstance($aName, $allSongs, $aHomeAudioSystem, $aTitleForAlbum, $aReleaseDateForAlbum, $aGenreForAlbum, $aHomeAudioSystemForAlbum, $aArtistForAlbum)
  {
    $thisInstance = new AlbumTracklist();
    $thisInstance->__construct($aName, $allSongs, $aHomeAudioSystem);
    $thisInstance->songs = array();
    $didAddSongs = $thisInstance->setSongs($allSongs);
    if (!$didAddSongs)
    {
      throw new Exception("Unable to create AlbumTracklist, must have at least 1 songs");
    }
    $thisInstance->album = new Album($aTitleForAlbum, $aReleaseDateForAlbum, $aGenreForAlbum, $aHomeAudioSystemForAlbum, $aArtistForAlbum, $thisInstance);
    return $thisInstance;
  }
  private function callParentConstructor($aName, $allSongs, $aHomeAudioSystem)
  {
    parent::__construct($aName, $allSongs, $aHomeAudioSystem);
  }


  //------------------------
  // INTERFACE
  //------------------------

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

  public function getAlbum()
  {
    return $this->album;
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

  public function addSongVia($aTitle, $aDuration, $aArtist)
  {
    return new Song($aTitle, $aDuration, $aArtist, $this);
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $existingAlbumTracklist = $aSong->getAlbumTracklist();
    $isNewAlbumTracklist = $existingAlbumTracklist != null && $this !== $existingAlbumTracklist;

    if ($isNewAlbumTracklist && $existingAlbumTracklist->numberOfSongs() <= self::minimumNumberOfSongs())
    {
      return $wasAdded;
    }

    if ($isNewAlbumTracklist)
    {
      $aSong->setAlbumTracklist($this);
    }
    else
    {
      $this->songs[] = $aSong;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSong($aSong)
  {
    $wasRemoved = false;
    //Unable to remove aSong, as it must always have a albumTracklist
    if ($this === $aSong->getAlbumTracklist())
    {
      return $wasRemoved;
    }

    //albumTracklist already at minimum (1)
    if ($this->numberOfSongs() <= self::minimumNumberOfSongs())
    {
      return $wasRemoved;
    }

    unset($this->songs[$this->indexOfSong($aSong)]);
    $this->songs = array_values($this->songs);
    $wasRemoved = true;
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->songs) > 0)
    {
      $aSong = $this->songs[count($this->songs) - 1];
      $aSong->delete();
      unset($this->songs[$this->indexOfSong($aSong)]);
      $this->songs = array_values($this->songs);
    }
    
      
    $existingAlbum = $this->album;
    $this->album = null;
    if ($existingAlbum != null)
    {
      $existingAlbum->delete();
    }
    parent::delete();
  }

}
?>