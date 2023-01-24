package song;

//SongLib by Haoran Lyu and Chris Velasquez

import java.util.Comparator;

import song.Song;

public class SongComparator implements Comparator<Song>{

	@Override
	public int compare(Song song1, Song song2) {
		return song1.getTitle().compareToIgnoreCase(song2.getTitle());
	}
}
