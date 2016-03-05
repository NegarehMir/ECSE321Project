<?php
error_reporting(0);
require_once __DIR__.'/controller/Controller.php';
session_start();

$_SESSION["errorAddPlaylistName"] = "";
$_SESSION["errorAddPlaylistSongs"] = "";

try{
  $playlist = null;

  $c = new Controller();

  if(isset($_POST['playlistName']))
  {
    $playlistName = $_POST['playlistName'];
  }

  if(isset($_POST['check_list']))
  {
    $songs = $_POST['check_list'];
  }

  $c->createPlaylist($playlistName, $songs);
}catch(Exception $e){

  if(strpos($e, "{{playlistName}}"))
  {
    $_SESSION["errorAddPlaylistName"] = "Playlist Name Cannot Be Empty!";
  }

  if(strpos($e, "{{playlistSongs}}"))
  {
    $_SESSION["errorAddPlaylistSongs"] = "Must Have at Least one Song!";
  }
}

?>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=index.php?page=addPlaylist" />
</head>
</html>
