package song;

//SongLib by Haoran Lyu and Chris Velasquez

import java.util.Comparator;

public class Song implements Comparator<Song>{
	
	private String title;
	private String artist;
	private String album;
	private String year;
	
	public Song(String title, String artist, String album, String year)
	{
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}

	public String getYear() {
		return this.year;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public int compare(Song song1, Song song2) {
		return song1.getTitle().compareToIgnoreCase(song2.getTitle());
	}
}


