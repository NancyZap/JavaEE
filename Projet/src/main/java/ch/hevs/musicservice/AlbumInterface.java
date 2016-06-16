package ch.hevs.musicservice;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

@Local
public interface AlbumInterface {
	
	Set<Album> showAlbumsByArtist(long id_artist);
	
	public void addAlbum(Album album, long idArtist);
	
	public boolean exist(String title);

	public Set<Song> showAlbumSongs(long id_album);

	public String addAlbumWithPerm(Album a, long idArtist);

}
