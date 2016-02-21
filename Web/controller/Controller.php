<?php

require_once(__DIR__.'/../persistence/PersistenceHomeAudioSystem.php');
require_once(__DIR__.'/../model/Album.php');
require_once(__DIR__.'/../model/Playlist.php');
require_once(__DIR__.'/../model/AlbumTracklist.php');
require_once(__DIR__.'/../model/Artist.php');
require_once(__DIR__.'/../model/Song.php');
require_once(__DIR__.'/../model/Genre.php');
require_once(__DIR__.'/../model/HomeAudioSystem.php');

class Controller
{
  public function __contruct(){}

    public function createAlbum($albumName, $albumArtist, $releaseDate, $genre, $songs)
    {
      $errors = "";

      if($albumName == null || trim($albumName) == "")
      {
        $errors .= "{{albumName}}Album Name Cannot Be Empty!";
      }

      if($albumArtist == null || trim($albumArtist) == "")
      {
        $errors.= "{{albumArtist}}Album Artist Cannot Be Empty!";
      }

      if($releaseDate == null || trim($releaseDate) == "")
      {
        $errors.= "{{releaseDate}}Album Release Date Cannot Be Empty!";
      }

      if(count($errors)) throw new Exception($errors);

      else
      {
        $pm = new PersistenceHomeAudioSystem();

        $has = $pm->loadDataFromStore();

        if($has->hasArtist())
        {

        }

        $album = new Album($albumName, $releaseDate, $genre);

        $has->addAlbum($album);

        $pm->writeDataToStore($has);
      }


    }
}
 ?>
