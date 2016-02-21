<?php
error_reporting(0);
session_start();


  if(isset($_POST['id']) && $_POST['id'] != "")
  {
    unset($_SESSION['songs'][$_POST['id']]); // remove item at index 0
    $_SESSION['songs'] = array_values($_SESSION['songs']);
  }

  echo json_encode($_SESSION['songs']);
  ?>
