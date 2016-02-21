<div class="form-wrapper">
  <form action="addAlbum.php" method="post">

    <div class="form-group">
      <label for="albumName" class="boot-label">Album Name</label>
      <input type="text" class="form-control custom-input" id="albumName" placeholder="Album Name" name="albumName">
    </div>

    <div class="form-group">
      <label for="albumArtist" class="boot-label">Album Artist</label>
      <input type="text" class="form-control custom-input" id="albumArtist" placeholder="Album Artist" name="albumArtist">
    </div>

    <div class="form-group">
      <label for="albumArtist" class="boot-label">Album Genre</label>
      <input type="text" class="form-control custom-input" id="albumArtist" placeholder="Album Genre" name="albumGenre">
    </div>

    <div class="form-group">
      <label for="datepicker" class="boot-label">Release Date</label>
      <input type="text" class="form-control custom-input" id="datepicker" placeholder="Album release Date" name="releaseDate">
    </div>

    <ul class="inline center">
      <li>
        <div class="mdl-textfield mdl-js-textfield">
          <input class="mdl-textfield__input" type="text" id="songInput">
          <label class="mdl-textfield__label" for="songInput">Add Song</label>
        </div>
      </li>

      <li>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored addSongButton" type="button">
          Add Song
        </button>
      </li>
    </ul>

    <input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit">

  </form>
</div>
