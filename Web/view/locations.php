<a href="?page=addLocation" class="button">Add Location</a>

<?php
$locations = $has->getLocations();

if(count($locations)==0)
{
  ?>
  <h3 style="text-align: center;">No Locations Have Been Added</h3>
  <?php
}

else
{

  ?>
  <table style="width: 50%; margin:10px auto">
    <thead>
      <tr>
        <th>Location Name</th>
        <th>Volume</th>
        <th>Muted</th>
      </tr>
    </thead>
    <tbody>

  <?php
  foreach($locations as $location)
  {
    ?>
      <tr>
        <td><a href='/location?loc=<?=htmlentities($location->getName())?>'><?=$location->getName()?></a></td>
        <td><?=$location->getVolume()?></td>
        <td><?php $mute = ($location->getMute() == 1) ? "true" : "False" ; echo $mute;?></td>
      </tr>
    <?php
  }

  ?>

    </tbody>
  </table>

  <?php
}
?>
