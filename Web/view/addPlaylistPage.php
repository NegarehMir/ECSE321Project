<a href="?page=playlist" class="button">Cancel</a>

<div class="form-wrapper">
  <form action="addPlaylist.php" method="post">

    <div>
      <label>Playlist Name
      <input type="text" placeholder="Playlist Name" name="playlistName">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddPlaylistName"])) echo $_SESSION["errorAddPlaylistName"];?></p>
    </div>
    <h3>Select Some Songs!</h3>
    <p class="error"><?php if(isset($_SESSION["errorAddPlaylistSongs"])) echo $_SESSION["errorAddPlaylistSongs"];?></p>
    <table class="songs-table" style="width: 90%; margin:50px auto">
      <thead>
        <tr>
          <th>Checked</th>
          <th>Song</th>
          <th>Artist</th>
          <th>Duration</th>
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
          <td><input name="check_list[]" class="select" type="checkbox" value="<?=$song->getTitle()?>"></td>
          <td><?=$song->getTitle()?></td>
          <td><?=$artist->getName()?></td>
          <td><?=$song->getDuration()?></td>
          <td></td>
        </tr>
        <?php
      }
    }
    ?>
    </tbody>
    </table>

    <input type="submit" class="button">
  </form>
