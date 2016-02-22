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



    ?>
    <meta charset="utf-8">
    <title>Super Cool Home Audio System</title>
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.1/material.blue-orange.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script defer src="https://code.getmdl.io/1.1.1/material.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="resources/js/global.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/global.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
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


    <div class="demo-layout-transparent mdl-layout mdl-js-layout">
      <header class="mdl-layout__header mdl-layout__header--transparent">
        <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">Home Audio System</span>
          <div class="mdl-layout-spacer"></div>
        </div>
      </header>
      <main class="mdl-layout__content">

        <div class="mdl-grid">
          <div class="mdl-cell mdl-cell--2-col"></div>

          <div class="mdl-cell mdl-cell--8-col">
            <div class="content-card mdl-card mdl-shadow--2dp">
              <div class="mdl-card__actions mdl-card--border">

                <div class="mdl-tabs mdl-js-tabs mdl-js-ripple-effect">
                  <div class="mdl-tabs__tab-bar">
                      <a href="#addAlbum" class="mdl-tabs__tab is-active">Add Album</a>
                      <a href="#songs" class="mdl-tabs__tab">Songs</a>
                      <a href="#playlists" class="mdl-tabs__tab">Playlists</a>
                  </div>

                  <div class="mdl-tabs__panel is-active" id="addAlbum">
                    <?php
                      require "view/addAlbumPage.php";
                    ?>
                  </div>

                  <div class="mdl-tabs__panel" id="songs">
                    <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp" style="width: 50%; margin:0 auto">
                      <thead>
                        <tr>
                          <th class="mdl-data-table__cell--non-numeric">Song</th>
                          <th>Artist</th>
                          <th>Album</th>
                        </tr>
                      </thead>
                      <tbody>
                    <?php

                    $artists = $has->getArtists();

                    foreach($artists as $artist){
                      $songs = $artist->getSongs();
                      foreach($songs as $song)
                      {
                        ?>
                        <tr>
                          <td class="mdl-data-table__cell--non-numeric"><?=$song->getTitle()?></td>
                          <td><?=$artist->getName()?></td>
                          <td></td>
                        </tr>
                        <?php
                      }
                    }
                    ?>
                  </tbody>
                  </div>
                  <div class="mdl-tabs__panel" id="playlists">
                    <?php
                    ?>
                  </div>
                </div>

              </div>
              <div class="mdl-card__menu">
                <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                  <i class="material-icons">share</i>
                </button>
              </div>
            </div>
          </div>

          <div class="mdl-cell mdl-cell--2-col"></div>
        </div>

      </main>
    </div>


  </body>
</html>