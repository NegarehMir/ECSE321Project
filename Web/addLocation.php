<?php
//error_reporting(0);
require_once __DIR__.'/controller/Controller.php';
session_start();

$_SESSION["errorAddLocationName"];

try{
  $location = null;

  $c = new Controller();

  if(isset($_POST['locationName']))
  {
    $locationName = $_POST['locationName'];
  }

  $c->createLocation($locationName);
}catch(Exception $e){

  if(strpos($e, "{{locationName}}"))
  {
    $_SESSION["errorAddLocation"] = "Location Name is Invalid";
  }

  if(strpos($e, "{{locationNameAlreadyExists}}"))
  {
    $_SESSION["errorAddLocation"] = "Location Name Already Exists";
  }
}
?>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=index.php?page=addLocation" />
</head>
</html>
