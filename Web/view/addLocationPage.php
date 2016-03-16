<a href="?page=location" class="button">Cancel</a>

<div class="form-wrapper">
  <form action="addLocation.php" method="post">
    <div>
      <label>Location Name
      <input type="text" placeholder="Location Name" name="locationName">
      </label>
      <p class="error"><?php if(isset($_SESSION["errorAddLocationName"])) echo $_SESSION["errorAddLocationName"];?></p>
    </div>

    <input type="submit" class="button">
  </form>
</div>
