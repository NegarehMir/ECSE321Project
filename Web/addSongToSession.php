<?php
error_reporting(0);
session_start();

if(isset($_SESSION['songs']) && is_array($_SESSION['songs']))
{
  if(isset($_POST['song']) && $_POST['song'] != "")
  {
    array_push($_SESSION['songs'], $_POST['song']);
  }
}

else
{
  $_SESSION['songs'] = [$_POST['song']];
}

echo json_encode([array_search($_POST['song'], $_SESSION['songs'])]);
?>
