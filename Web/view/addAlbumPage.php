<div class="form-wrapper">
  <form action="addAlbum.php" method="post">

    <div class="form-group">
      <label for="albumName" class="boot-label">Album Name</label>
      <input type="text" class="form-control custom-input" id="albumName" placeholder="Album Name" name="albumName">
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumName"])) echo $_SESSION["errorAddAlbumName"];?></p>
    </div>

    <div class="form-group">
      <label for="albumArtist" class="boot-label">Album Artist</label>
      <input type="text" class="form-control custom-input" id="albumArtist" placeholder="Album Artist" name="albumArtist">
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumArtist"])) echo $_SESSION["errorAddAlbumArtist"];?></p>
    </div>

    <!-- <div class="form-group">
      <label for="albumArtist" class="boot-label">Album Genre</label>
      <input type="text" class="form-control custom-input" id="albumArtist" placeholder="Album Genre" name="albumGenre">
    </div> -->
    <div class="form-group">
      <label for="genre" class="boot-label">Album Genre</label>
      <select class="form-control" style="width: 100% !important;" name="genre">
        <option>Rock</option>
      </select>
    </div>

    <div class="form-group">
      <label for="datepicker" class="boot-label">Release Date</label>
      <input type="text" class="form-control custom-input" id="datepicker" placeholder="Album release Date" name="releaseDate">
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumDate"])) echo $_SESSION["errorAddAlbumDate"];?></p>
    </div>

    <ul class="inline center">

      <li>
        <div class="mdl-textfield mdl-js-textfield">
          <input class="mdl-textfield__input" type="text" id="songInput">
          <label class="mdl-textfield__label" for="songInput">Add Song</label>
        </div>
      </li>

      <li>
        <div class="mdl-textfield mdl-js-textfield">
          <input class="mdl-textfield__input" type="text" id="durationInput">
          <label class="mdl-textfield__label" for="durationInput">Duration</label>
        </div>
      </li>

      <li>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored addSongButton" type="button">
          Add Song
        </button>
      </li>
    </ul>
    <p class="error"><?php if(isset($_SESSION["errorAddAlbumSongs"])) echo $_SESSION["errorAddAlbumSongs"];?></p>

    <table class="mdl-data-table mdl-shadow--2dp" style="margin:0 auto; width: 50%">
      <thead>
        <tr>
          <th>Song</th>
          <th>Duration</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody class="addedSongs">
        <?php
        if(isset($_SESSION['songs']) && is_array($_SESSION['songs']))
        {
          $count = 0;
          foreach($_SESSION['songs'] as $song)
          {
            ?>
            <tr id='song_row<?=$count?>'>
             <td>
             <?=$song[0]?>
             </td>
             <td>
             <?=$song[1]?>
             </td>
             <td class='delete_row'>
             <span class='btn btn-danger remove_class' id='<?=$count?>'>
             <i class='material-icons'>clear</i>
             </span>
             </td>
            </tr>
            <?php

            $count++;
          }
        }
        ?>
      </tbody>
    </table>


    <input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit">

  </form>
</div>
