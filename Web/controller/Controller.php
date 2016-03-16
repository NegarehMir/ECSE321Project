<?php

require_once(__DIR__.'/../persistence/PersistenceHomeAudioSystem.php');
require_once(__DIR__.'/../model/Album.php');
require_once(__DIR__.'/../model/Playlist.php');
require_once(__DIR__.'/../model/AlbumTracklist.php');
require_once(__DIR__.'/../model/Artist.php');
require_once(__DIR__.'/../model/Location.php');
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

      if($genre == null || trim($genre) == "")
      {
       $errors.= "{{genre}}Album Genre Cannot Be Empty!";
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

        else
        {
          $artist = $searchArtist;
        }

        // $searchGenre = $this->searchArtists($genre, $has);
        //
        // if($searchGenre == false)
        // {
        //   $genre = new Genre($genre, $has);
        //   $has->addGenre($genre);
        // }
        //
        // else
        // {
        //   $genre = $searchGenre;
        // }

        $albumSongs = new AlbumTracklist();

        foreach($songs as $song)
        {
          $newSong = new Song($song[0], $song[1], $artist, $albumSongs);
          $artist->addSong($newSong);
          $albumSongs->addSong($newSong);
        }

        $album = new Album($albumName, $releaseDate, $genre, $has, $artist, $albumSongs);

        $has->addAlbum($album);

        $artist->addAlbum($album);

        $pm->writeDataToStore($has);
      }


    }


    public function createPlaylist($playlistName, $playlistSongs)
    {
      $errors = "";

      if($playlistName == null || trim($playlistName) == "")
      {
        $errors .= "{{playlistName}}";
      }

      if(!is_array($playlistSongs) || count($playlistSongs) == 0)
      {
        $errors .= "{{playlistSongs}}";
      }

      if(strlen($errors)>0) throw new Exception($errors);

      else
      {
        $pm = new PersistenceHomeAudioSystem();

        $has = $pm->loadDataFromStore();

        $songs = [];

        foreach($playlistSongs as $song)
        {
          $songOobject = $this->searchSong($song, $has);
          if($songOobject != false) $songs[] = $songOobject;
        }

        $playlist = new Playlist($playlistName, $songs, $has);

        $has->addPlaylist($playlist);

        $pm->writeDataToStore($has);
      }

    }

    public function createLocation($locationName)
    {
      $errors = "";

      $pm = new PersistenceHomeAudioSystem();

      $has = $pm->loadDataFromStore();

      if($locationName == null || trim($locationName) == "")
      {
        $errors .= "{{locationName}}";
      }

      else if($this->searchLocation($locationName, $has) != false)
      {
        $errors .= "{{locationNameAlreadyExists}}";
      }

      if(strlen($errors) > 0) throw new Exception($errors);

      else
      {
        $newLocation = new Location($locationName, 100, true, $has);

        $has->addLocation($newLocation);

        $pm->writeDataToStore($has);
      }
    }

    /*************HELPER FUNCTIONS************************/
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

    public function searchGenre($key, $has)
    {
      $genres = $has->getGenres();

      foreach($genres as $genre)
      {
        if($genre->getName() == $key)
        {
          return $genre;
        }
      }

      return false;
    }

    public function searchSong($key, $has)
    {
      $artists = $has->getArtists();

      foreach($artists as $artist)
      {
        $songs = $artist->getSongs();
        foreach($songs as $song)
        {
          if($song->getTitle() == $key)
          {
            return $song;
          }
        }
      }
      return false;
    }

    public function searchLocation($key, $has)
    {
      $locations = $has->getLocations();

      foreach($locations as $location)
      {
        if($location->getName() == $key)
        {
          return $location;
        }
      }

      return false;
    }
}
 ?>
