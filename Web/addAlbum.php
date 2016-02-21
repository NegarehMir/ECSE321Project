<?php
require_once __DIR__.'/controller/Controller.php';
session_start();

$_SESSION["errorAddAlbumName"] = "";
$_SESSION["errorAddAlbumArtist"] = "";
$_SESSION["errorAddAlbumDate"] = "";
$_SESSION["errorAddAlbumSongs"] = "";
$_SESSION["errorAddAlbumPhoto"] = "";

print_r($_POST);
try{
  $album = null;

  $c = new Controller();

  if(isset($_POST['albumName']))
  {
    $albumName = $_POST['albumName'];
  }

  if(isset($_POST['albumArtist']))
  {
    $albumArtist = $_POST['albumArtist'];
  }

  if(isset($_POST['albumDate']))
  {
    $releaseDate = $_POST['releaseDate'];
  }

  if(isset($_POST['albumsPhoto']))
  {
    $albumsPhoto = $_POST['albumsPhoto'];
  }

  if(isset($_POST['albumSongs']))
  {
    $songs = $_POST['albumSongs'];
  }

  $c->createAlbum($albumName, $albumArtist, $releaseDate, $genre, $songs);

}catch(Exception $e){

}

?>


<!-- <!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=/ECSE%20321%20Project/Group01/Web/" />
</head>
</html> -->
