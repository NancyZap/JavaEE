package ch.hevs.musicservice;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

@Local
public interface SongInterface {

	Set<Song> showSongsByAlbum(long id_album);
	
	public boolean exist(String title);
	
	public void addSong(Song song, long idAlbum);

	public String addSongWithPerm(Song s, long idAlbum);
}
