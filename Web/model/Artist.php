<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Artist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private $name;

  //Artist Associations
  private $albums;
  private $songs;
  private $homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aHomeAudioSystem)
  {
    $this->name = $aName;
    $this->albums = array();
    $this->songs = array();
    $didAddHomeAudioSystem = $this->setHomeAudioSystem($aHomeAudioSystem);
    if (!$didAddHomeAudioSystem)
    {
      throw new Exception("Unable to create artist due to homeAudioSystem");
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

  public static function minimumNumberOfAlbums()
  {
    return 0;
  }

  public function addAlbumVia($aTitle, $aHomeAudioSystem, $aGenre, $aReleaseDate)
  {
    return new Album($aTitle, $aHomeAudioSystem, $aGenre, $aReleaseDate, $this);
  }

  public function addAlbum($aAlbum)
  {
    $wasAdded = false;
    if ($this->indexOfAlbum($aAlbum) !== -1) { return false; }
    $existingArtist = $aAlbum->getArtist();
    $isNewArtist = $existingArtist != null && $this !== $existingArtist;
    if ($isNewArtist)
    {
      $aAlbum->setArtist($this);
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
    //Unable to remove aAlbum, as it must always have a artist
    if ($this !== $aAlbum->getArtist())
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

  public static function minimumNumberOfSongs()
  {
    return 0;
  }

  public function addSong($aSong)
  {
    $wasAdded = false;
    if ($this->indexOfSong($aSong) !== -1) { return false; }
    $this->songs[] = $aSong;
    if ($aSong->indexOfArtist($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aSong->addArtist($this);
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
    if ($aSong->indexOfArtist($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aSong->removeArtist($this);
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
      $existingHomeAudioSystem->removeArtist($this);
    }
    $this->homeAudioSystem->addArtist($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->albums as $aAlbum)
    {
      $aAlbum->delete();
    }
    $copyOfSongs = $this->songs;
    $this->songs = array();
    foreach ($copyOfSongs as $aSong)
    {
      if ($aSong->numberOfArtists() <= Song::minimumNumberOfArtists())
      {
        $aSong->delete();
      }
      else
      {
        $aSong->removeArtist($this);
      }
    }
    $placeholderHomeAudioSystem = $this->homeAudioSystem;
    $this->homeAudioSystem = null;
    $placeholderHomeAudioSystem->removeArtist($this);
  }

}
?>