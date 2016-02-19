<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Album
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private $title;
  private $releaseDate;
  private $genre;

  //Album Associations
  private $homeAudioSystem;
  private $artist;
  private $albumTracklist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle = null, $aReleaseDate = null, $aGenre = null, $aHomeAudioSystem = null, $aArtist = null, $aAlbumTracklist = null)
  {
    if (func_num_args() == 0) { return; }

    $this->title = $aTitle;
    $this->releaseDate = $aReleaseDate;
    $this->genre = $aGenre;
    $didAddHomeAudioSystem = $this->setHomeAudioSystem($aHomeAudioSystem);
    if (!$didAddHomeAudioSystem)
    {
      throw new Exception("Unable to create album due to homeAudioSystem");
    }
    $didAddArtist = $this->setArtist($aArtist);
    if (!$didAddArtist)
    {
      throw new Exception("Unable to create album due to artist");
    }
    if ($aAlbumTracklist == null || $aAlbumTracklist->getAlbum() != null)
    {
      throw new Exception("Unable to create Album due to aAlbumTracklist");
    }
    $this->albumTracklist = $aAlbumTracklist;
  }
  public static function newInstance($aTitle, $aReleaseDate, $aGenre, $aHomeAudioSystem, $aArtist, $aNameForAlbumTracklist, $allSongsForAlbumTracklist, $aHomeAudioSystemForAlbumTracklist)
  {
    $thisInstance = new Album();
    $thisInstance->title = $aTitle;
    $thisInstance->releaseDate = $aReleaseDate;
    $thisInstance->genre = $aGenre;$this->homeAudioSystems = array();
    $this->homeAudioSystems[] = $aHomeAudioSystem;$this->artists = array();
    $this->artists[] = $aArtist;
    $thisInstance->albumTracklist = new AlbumTracklist($aNameForAlbumTracklist, $allSongsForAlbumTracklist, $aHomeAudioSystemForAlbumTracklist, $thisInstance);
    return $thisInstance;
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

  public function setReleaseDate($aReleaseDate)
  {
    $wasSet = false;
    $this->releaseDate = $aReleaseDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setGenre($aGenre)
  {
    $wasSet = false;
    $this->genre = $aGenre;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getReleaseDate()
  {
    return $this->releaseDate;
  }

  public function getGenre()
  {
    return $this->genre;
  }

  public function getHomeAudioSystem()
  {
    return $this->homeAudioSystem;
  }

  public function getArtist()
  {
    return $this->artist;
  }

  public function getAlbumTracklist()
  {
    return $this->albumTracklist;
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
      $existingHomeAudioSystem->removeAlbum($this);
    }
    $this->homeAudioSystem->addAlbum($this);
    $wasSet = true;
    return $wasSet;
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
      $existingArtist->removeAlbum($this);
    }
    $this->artist->addAlbum($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderHomeAudioSystem = $this->homeAudioSystem;
    $this->homeAudioSystem = null;
    $placeholderHomeAudioSystem->removeAlbum($this);
    $placeholderArtist = $this->artist;
    $this->artist = null;
    $placeholderArtist->removeAlbum($this);
    $existingAlbumTracklist = $this->albumTracklist;
    $this->albumTracklist = null;
    if ($existingAlbumTracklist != null)
    {
      $existingAlbumTracklist->delete();
    }
  }

}
?>