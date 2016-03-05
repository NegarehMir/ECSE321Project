
<a href="?page=album" class="button">Cancel</a>
<div class="form-wrapper">
  <form action="addAlbum.php" method="post">

    <div>
      <label>Album Name
      <input type="text" placeholder="Album Name" name="albumName">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumName"])) echo $_SESSION["errorAddAlbumName"];?></p>
    </div>

    <div>
      <label>Album Artist
      <input type="text" id="albumArtist" placeholder="Album Artist" name="albumArtist">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumArtist"])) echo $_SESSION["errorAddAlbumArtist"];?></p>
    </div>

    <!-- <div class="form-group">
      <label for="albumArtist" class="boot-label">Album Genre</label>
      <input type="text" id="albumArtist" placeholder="Album Genre" name="albumGenre">
    </div> -->
    <div>
      <label>Album Genre
      <input type="text" id="albumGenre" placeholder="Album Genre" name="albumGenre">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumGenre"])) echo $_SESSION["errorAddAlbumGenre"];?></p>
    </div>

    <div>
      <label>Release Date
      <input type="text" id="datepicker" placeholder="Album release Date" name="releaseDate">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddAlbumDate"])) echo $_SESSION["errorAddAlbumDate"];?></p>
    </div>

    <ul class="inline center">

      <li>
        <div>

          <label>Add Song
          <input type="text" id="songInput">
          </label>
        </div>
      </li>

      <li>
        <div>
          <label class="mdl-textfield__label" for="durationInput">Duration
          <input type="text" id="durationInput">
          </label>
        </div>
      </li>

      <li>
        <button class="button addSongButton" type="button">
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
             <span class='alert button remove_class' id='<?=$count?>'>
             X
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


    <input class="button" type="submit">

  </form>
</div>
