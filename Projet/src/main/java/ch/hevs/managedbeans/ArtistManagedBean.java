package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.musicservice.ArtistInterface;

/**
 * ArtistManagedBean.java
 * 
 */

public class ArtistManagedBean
{
	private List<Artist> artistsList;
	private List<String> artistNames;
	// TODO: vérifier si OK 
	//private List<Album> albumsList;
	private Set<Album> albumsList;
	private String artistName;
	private boolean band;
	

	private Artist chosenArtist;

	private ArtistInterface artist;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		artist = (ArtistInterface) ctx.lookup("java:global/projet-0.0.1-SNAPSHOT/ArtistBean!ch.hevs.musicservice.ArtistInterface");    	

		// get artists
		artistsList = artist.getArtists();
		this.artistNames = new ArrayList<String>();
		for (Artist artist : artistsList) {
			this.artistNames.add(artist.getName());
		}

	}

	// Show the albums of an artist
	public String showArtistAlbums(long id_artist)
	{
		try
		{
			albumsList = artist.showArtistAlbums(id_artist);
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}
	
	// Add artist
	public void addArtist() {
		Artist a = new Artist(this.artistName, this.band);
		artist.addArtist(a);
	}

	// Getters & setters
	public boolean isBand() {
		return band;
	}

	public void setBand(boolean band) {
		this.band = band;
	}
	
	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(final String artistName) {
		this.artistName = artistName;
	}

	public List<Artist> getArtists() {
		return artistsList;
	}

	public List<String> getArtistNames() {
		return artistNames;
	}

	public Artist getChosenArtist() {
		return chosenArtist;
	}

	public void setChosenArtist(Artist chosenArtist) {
		this.chosenArtist = chosenArtist;
	}
	/*
	public List<Album> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(List<Album> albumsList) {
		this.albumsList = albumsList;
	}
	 */

	public Set<Album> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(Set<Album> albumsList) {
		this.albumsList = albumsList;
	}


} 

