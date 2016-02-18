<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-f2a13e6 modeling language!*/

class Genre
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Genre Attributes
  private $name;

  //Genre Associations
  private $homeAudioSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aHomeAudioSystem)
  {
    $this->name = $aName;
    $didAddHomeAudioSystem = $this->setHomeAudioSystem($aHomeAudioSystem);
    if (!$didAddHomeAudioSystem)
    {
      throw new Exception("Unable to create genre due to homeAudioSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getName()
  {
    return $this->name;
  }

  public function getHomeAudioSystem()
  {
    return $this->homeAudioSystem;
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
      $existingHomeAudioSystem->removeGenre($this);
    }
    $this->homeAudioSystem->addGenre($this);
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
    $placeholderHomeAudioSystem->removeGenre($this);
  }

}
?>