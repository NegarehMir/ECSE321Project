<?php

require_once(__DIR__.'/../persistence/PersistenceHomeAudioSystem.php');
require_once(__DIR__.'/../model/Album.php');
require_once(__DIR__.'/../model/Playlist.php');
require_once(__DIR__.'/../model/Artist.php');
require_once(__DIR__.'/../model/Song.php');
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
        
        $genreConstant = matchGenreStringToConstant($genre);


        $album = new Album($albumName, $has, $releaseDate, $genreConstant, $artist);
        
        foreach($songs as $song)
        {
          $newSong = new Song($song[0], $song[1], $artist, $albumSongs);
          $artist->addSong($newSong);
          $album->addSong($newSong);
        }

        

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

        $playlist = new Playlist($playlistName, $has);

        $has->addPlaylist($playlist);

        $pm->writeDataToStore($has);
      }

    }

    
    
    private function matchGenreStringToConstant($genreString) {
    	switch ($genreString) {
    		case "Alternative":
    			return Genres.Alternative;
    		case "Classical":
    			return Genres.Classical;
    		case "Country":
    			return Genres.Country;
    		case "Electronic":
    			return Genres.Electronic;
    		case "Rap":
    			return Genres.Rap;
    		case "Pop":
    			return Genres.Pop;
    		case "Rock":
    			return Genres.Rock;
    		case "Jazz":
    			return Genres.Jazz;
    	}
    	
    	return $genreString;
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
}
 ?>
