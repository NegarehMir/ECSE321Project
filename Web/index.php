<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Super Cool Home Audio System</title>
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.1/material.blue-orange.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script defer src="https://code.getmdl.io/1.1.1/material.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
                    <?php
                    ?>
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
