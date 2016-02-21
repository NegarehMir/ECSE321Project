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

      if(count($songs) == 0)
      {
        $errors.= "{{songs}}Must Have at Least One Song!";
      }

      if(strlen($errors)>0) throw new Exception($errors);

      else
      {
        $pm = new PersistenceHomeAudioSystem();

        $has = $pm->loadDataFromStore();

        $searchArtist = $this->searchArtists($albumArtist, $has);

        if( $searchArtist == false)
        {
          $artist = new Artist($albumArtist, $has);
          $has->addArtist($artist);
        }

        else $artist = $searchArtist;

        $albumSongs = new AlbumTracklist();

        foreach($songs as $song)
        {
          $newSong = new Song($song, 500, $artist, $albumSongs);
          $artist->addSong($newSong);
          $albumSongs->addSong($newSong);
        }

        $album = new Album($albumName, $releaseDate, $genre, $has, $artist, $albumSongs);

        $has->addAlbum($album);

        $artist->addAlbum($album);

        $pm->writeDataToStore($has);
      }


    }

    public function searchArtists($key, $has)
    {
      $artists = $has->getArtists();

      foreach($artists as $artist)
      {
        if($artist->getName() == $key)
        {
          return $artist;
        }
      }

      return false;
    }
}
 ?>
