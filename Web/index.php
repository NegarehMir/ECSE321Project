<!DOCTYPE html>
<html>
  <head>
    <?php

    require_once __DIR__.'/persistence/PersistenceHomeAudioSystem.php';
    require_once __DIR__.'/model/HomeAudioSystem.php';
    require_once __DIR__.'/model/Artist.php';
    require_once __DIR__.'/model/Album.php';
    require_once __DIR__.'/model/Song.php';
    require_once __DIR__.'/model/Playlist.php';
    require_once __DIR__.'/model/AlbumTracklist.php';

    error_reporting(0);
    session_start();

    $pm = new PersistenceHomeAudioSystem();

    $has = $pm->loadDataFromStore();

    /** ROUTING **/

    if(isset($_GET['page']) && ($_GET['page'] == 'addAlbum' || $_GET['page'] == 'album'))
    {
      $tab = "albums";
    }

    else if(isset($_GET['page']) && $_GET['page'] == 'addPlaylist')
    {
      $tab = "playlists";
    }

    else
    {
      $tab = "songs";
    }

    ?>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Super Cool Home Audio System</title>
    <!-- <link rel="stylesheet" href="https://code.getmdl.io/1.1.1/material.blue-orange.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script defer src="https://code.getmdl.io/1.1.1/material.min.js"></script> -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="resources/js/global.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script src="resources/js/what-input.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/global.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/foundation.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/foundation-flex.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/foundation-rtl.min.css" />
    <script>
      $(function() {
        $( "#datepicker" ).datepicker();
        $( "#anim" ).change(function() {
          $( "#datepicker" ).datepicker( "option", "showAnim", $( this ).val() );
        });
      });
    </script>
  </head>
  <body>



    <div class="row" style="margin-top:100px;">
      <div class="small-4 columns"></div>

      <div class="small-12 columns">

        <div class="container">

          <ul class="tabs" data-tabs id="navigation-tabs">
            <li class="tabs-title <?php if($tab == "songs") {echo "is-active";}?>"><a href="#songs" <?php if($tab == "songs") {echo 'aria-selected="true"';}?>>Songs</a></li>
            <li class="tabs-title <?php if($tab == "albums") {echo "is-active";}?>"><a href="#albums" <?php if($tab == "albums") {echo 'aria-selected="true"';}?>>Albums</a></li>
            <li class="tabs-title <?php if($tab == "playlists") {echo "is-active";}?>"><a href="#playlists" <?php if($tab == "playlists") {echo 'aria-selected="true"';}?>>Playlists</a></li>
          </ul>

          <div class="tabs-content" data-tabs-content="navigation-tabs">
            <div class="tabs-panel <?php if($tab == "songs") {echo "is-active";}?>" id="songs">
              <?php require "view/songs.php" ?>
            </div>

            <div class="tabs-panel <?php if($tab == "albums") {echo "is-active";}?>" id="albums">
              <?php
                if(isset($_GET['page']) && $_GET['page'] == 'addAlbum')
                {
                  require "view/addAlbumPage.php";
                }
                else
                {
                  require "view/albums.php";
                }

              ?>
            </div>
            <div class="tabs-panel <?php if($tab == "playlists") {echo "is-active";}?>" id="playlists">
              <?php
              require "view/addAlbumPage.php";
              ?>
            </div>
          </div>
        </div>
        </div>
      <div class="small-2 columns"></div>
    </div>



  </body>
  <script src="resources/js/fastclick.js"></script>
  <script src="resources/js/modernizr.js"></script>
  <script>
   $(document).foundation();
 </script>
</html>
