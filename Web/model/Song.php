<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private $title;
  private $duration;

  //Song Associations
  private $artists;
  private $album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aDuration, $allArtists, $aAlbum)
  {
    $this->title = $aTitle;
    $this->duration = $aDuration;
    $this->artists = array();
    $didAddArtists = $this->setArtists($allArtists);
    if (!$didAddArtists)
    {
      throw new Exception("Unable to create Song, must have at least 1 artists");
    }
    $didAddAlbum = $this->setAlbum($aAlbum);
    if (!$didAddAlbum)
    {
      throw new Exception("Unable to create song due to album");
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

  public function getAlbum()
  {
    return $this->album;
  }

  public function isNumberOfArtistsValid()
  {
    $isValid = $this->numberOfArtists() >= self::minimumNumberOfArtists();
    return $isValid;
  }

  public static function minimumNumberOfArtists()
  {
    return 1;
  }

  public function addArtist($aArtist)
  {
    $wasAdded = false;
    if ($this->indexOfArtist($aArtist) !== -1) { return false; }
    $this->artists[] = $aArtist;
    if ($aArtist->indexOfSong($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aArtist->addSong($this);
      if (!$wasAdded)
      {
        array_pop($this->artists);
      }
    }
    return $wasAdded;
  }

  public function removeArtist($aArtist)
  {
    $wasRemoved = false;
    if ($this->indexOfArtist($aArtist) == -1)
    {
      return $wasRemoved;
    }

    if ($this->numberOfArtists() <= self::minimumNumberOfArtists())
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfArtist($aArtist);
    unset($this->artists[$oldIndex]);
    if ($aArtist->indexOfSong($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aArtist->removeSong($this);
      if (!$wasRemoved)
      {
        $this->artists[$oldIndex] = $aArtist;
        ksort($this->artists);
      }
    }
    $this->artists = array_values($this->artists);
    return $wasRemoved;
  }

  public function setArtists($newArtists)
  {
    $wasSet = false;
    $verifiedArtists = array();
    foreach ($newArtists as $aArtist)
    {
      if (array_search($aArtist,$verifiedArtists) !== false)
      {
        continue;
      }
      $verifiedArtists[] = $aArtist;
    }

    if (count($verifiedArtists) != count($newArtists) || count($verifiedArtists) < self::minimumNumberOfArtists())
    {
      return $wasSet;
    }

    $oldArtists = $this->artists;
    $this->artists = array();
    foreach ($verifiedArtists as $aNewArtist)
    {
      $this->artists[] = $aNewArtist;
      $removeIndex = array_search($aNewArtist,$oldArtists);
      if ($removeIndex !== false)
      {
        unset($oldArtists[$removeIndex]);
        $oldArtists = array_values($oldArtists);
      }
      else
      {
        $aNewArtist->addSong($this);
      }
    }

    foreach ($oldArtists as $anOldArtist)
    {
      $anOldArtist->removeSong($this);
    }
    $wasSet = true;
    return $wasSet;
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

  public function setAlbum($aAlbum)
  {
    $wasSet = false;
    if ($aAlbum == null)
    {
      return $wasSet;
    }
    
    $existingAlbum = $this->album;
    $this->album = $aAlbum;
    if ($existingAlbum != null && $existingAlbum != $aAlbum)
    {
      $existingAlbum->removeSong($this);
    }
    $this->album->addSong($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfArtists = $this->artists;
    $this->artists = array();
    foreach ($copyOfArtists as $aArtist)
    {
      $aArtist->removeSong($this);
    }
    $placeholderAlbum = $this->album;
    $this->album = null;
    $placeholderAlbum->removeSong($this);
  }

}
?>