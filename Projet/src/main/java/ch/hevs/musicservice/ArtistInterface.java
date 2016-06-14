package ch.hevs.musicservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;

@Local
public interface ArtistInterface {
	
	List<Artist> getArtists();
	
	public Set<Album> showArtistAlbums(long id_artist);
	
	public boolean exist(String name);
	
	public void addArtist(Artist artist);
	
	//TODO : DELETE METHOD
	
	//TODO : UPDATE METHOD
}
