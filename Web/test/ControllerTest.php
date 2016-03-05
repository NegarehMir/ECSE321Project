<?php
require_once __DIR__.'/../controller/Controller.php';
require_once __DIR__.'/../persistence/PersistenceHomeAudioSystem.php';

class ControllerTest extends PHPUnit_Framework_TestCase
{
	
	protected $hasController;
	protected $persistence;
	protected $has;
	
	protected function setUp()
	{
		$this->hasController = new Controller();
		$this->persistence = new PersistenceHomeAudioSystem();
		$this->has = $this->persistence->loadDataFromStore();
		$this->has->delete();
		$this->persistence->writeDataToStore($this->has);
	}
	
	protected function tearDown()
	{
		
	}
	
	public function testCreatePlaylist() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
		
		$playlistName = "Chill";
		
		try {
			$this->hasController->createPlaylist($playlistName);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
		
		
		// check file contents
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getPlaylists()));
		$this->assertEquals($name, $this->has->getPlaylist_index(0)->getName());
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
	}
	
	public function testCreatePlaylistNull() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
		
		$playlistName = null;
		
		$error = "";
		try {
			$this->hasController->createPlaylist($playlistName);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// check error
		$this->assertEquals("{{playlistName}}", $error);
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
	}
	
	
	public function testCreateParticipantEmpty() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
		
		$playlistName = "";
		
		$error = "";
		try {
			$this->hasController->createPlaylist($playlistName);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// check error
		$this->assertEquals("{{playlistName}}", $error);
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(0, count($this->has->getPlaylists()));
		$this->assertEquals(0, count($this->has->getArtists()));
		$this->assertEquals(0, count($this->has->getLocations()));
	}
	
	
	public function testAddSongToPlaylist() {
		$this->assertEquals(0, count($this->has->getPlaylists()));
		
		$this->hasController->createAlbum($albumName, $albumArtist, $releaseDate, $genre, $songs);
		
		$playlistTitle = "Work";
		try {
			$this->hasController->createPlaylist($playlistTitle);
		} catch (Exception $e) {
			$this->fail();
		}
		
		
		$this->has = $this->persistence->loadDataFromStore();
		$this->assertEquals(1, count($this->has->getPlaylists()));
	}
}