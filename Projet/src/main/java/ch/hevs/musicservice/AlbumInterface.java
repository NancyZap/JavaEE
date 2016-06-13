package ch.hevs.musicservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;

@Local
public interface AlbumInterface {
	
	List<Album> showAlbumsByArtist(long id_artist);
}
