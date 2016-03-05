
<a href="?page=addAlbum" class="button">Add Album</a>

<table class="mdl-data-table mdl-shadow--2dp" style="width: 50%; margin:50px auto">
  <thead>
    <tr>
      <th>Album</th>
      <th>Release Date</th>
      <th>Genre</th>
    </tr>
  </thead>
  <tbody>
    <?php

    $albums = $has->getAlbums();

    foreach($albums as $album)
    {
      ?>
        <tr>
          <td><?=$album->getTitle()?></td>
          <td><?=$album->getReleaseDate()?></td>
          <td><?=$album->getGenre()?></td>
        </tr>
      <?php
      }
      ?>
  </tbody>
</table>

<div class="reveal" id="addAlbumModal" data-reveal>

  <button class="close-button" data-close aria-label="Close modal" type="button">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
