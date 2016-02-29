<a href="?page=addPlaylist" class="button">Add Playlist</a>

<?php
$playlists = $has->getPlayLists();

if(count($playlists)==0)
{
  ?>
  <h3 style="text-align: center;">No Playlists Have Been Added</h3>
  <?php
}

else
{
  foreach($playlists as $playlist)
  {
    $songsInPlaylist = $playlist->getSongs();

    ?>
    <h3 style="text-align:center;"><?=$playlist->getName()?></h3>
    <table style="width: 50%; margin:10px auto">
      <thead>
        <tr>
          <th>Song</th>
          <th>Duration</th>
        </tr>
      </thead>
      <tbody>

    <?php

    foreach($songsInPlaylist as $song)
    {
      ?>
      <tr>
        <td><?=$song->getTitle()?></td>
        <td><?=$song->getDuration()?></td>
      </tr>
      <?php
    }

    ?>
    </tbody>
    </table>

    <?php

  }
}
 ?>
