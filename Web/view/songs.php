<table style="width: 50%; margin:50px auto">
  <thead>
    <tr>
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
