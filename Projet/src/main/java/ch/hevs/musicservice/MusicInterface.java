package ch.hevs.musicservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Artist;

@Local
public interface MusicInterface {
	
	List<Artist> getArtists();
}
