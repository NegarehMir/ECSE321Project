<?php
//error_reporting(0);
require_once __DIR__.'/controller/Controller.php';
session_start();

$_SESSION["errorAddAlbumName"] = "";
$_SESSION["errorAddAlbumArtist"] = "";
$_SESSION["errorAddAlbumDate"] = "";
$_SESSION["errorAddAlbumGenre"] = "";
$_SESSION["errorAddAlbumSongs"] = "";
$_SESSION["errorAddAlbumPhoto"] = "";

//print_r($_POST);
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

  if(isset($_POST['releaseDate']))
  {
    $releaseDate = $_POST['releaseDate'];
  }

  if(isset($_POST['albumsPhoto']))
  {
    $albumsPhoto = $_POST['albumsPhoto'];
  }

  if(isset($_POST['albumGenre']))
  {
    $genre = $_POST['albumGenre'];
  }

  if(isset($_SESSION['songs']))
  {
    $songs = $_SESSION['songs'];
    $_SESSION["songs"] = [];
  }

  $c->createAlbum($albumName, $albumArtist, $releaseDate, $genre, $songs);

}catch(Exception $e){

  //echo $e;

  if(strpos($e, "{{albumName}}"))
  {
    $_SESSION["errorAddAlbumName"] = "Album Name Cannot Be Empty!";
  }

  if(strpos($e, "{{albumArtist}}"))
  {
    $_SESSION["errorAddAlbumArtist"] = "Album Artist Cannot Be Empty!";
  }

  if(strpos($e, "{{releaseDate}}"))
  {
    $_SESSION["errorAddAlbumDate"] = "Album release Date Is Invalid!";
  }

  if(strpos($e, "{{songs}}"))
  {
    $_SESSION["errorAddAlbumSongs"]  = "Must Have at Least One Song!";
  }

  if(strpos($e, "{{genre}}"))
  {
    $_SESSION["errorAddAlbumGenre"]  = "Album Genre cannot be empty!";
  }

}

?>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=index.php?page=album" />
</head>
</html>
