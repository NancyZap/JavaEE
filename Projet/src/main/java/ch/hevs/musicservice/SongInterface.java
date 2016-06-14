package ch.hevs.musicservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

@Local
public interface SongInterface {

	List<Song> showSongsByAlbum(long id_album);
	
	//TODO: EVERYTHING ELSE
}
