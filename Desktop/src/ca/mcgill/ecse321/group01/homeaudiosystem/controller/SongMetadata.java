package ca.mcgill.ecse321.group01.homeaudiosystem.controller;

public class SongMetadata {
	private String title;
	private int duration;
	
	public SongMetadata(String name, int duration) {
		this.title = name;
		this.duration = duration;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getDuration() {
		return this.duration;
	}
}
