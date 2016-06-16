package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
	private Set<Album> albumsList;
	private String artistName;
	private long artistId;
	private boolean band;
	private Artist chosenArtist;
	private ArtistInterface artist;
	private String permission;




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
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,String> params = 
					fc.getExternalContext().getRequestParameterMap();
			artistName =  params.get("artistN"); 
			artistId = Long.valueOf(params.get("artistId")).longValue();
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}
	
	// Delete an artist
	public void deleteArtist(long id_artist)
	{
		permission = artist.deleteArtist(id_artist);
		artistsList = artist.getArtists();
	}


	// Add artist
	public void addArtist() {
		if(!artist.exist(this.artistName)){
			Artist a = new Artist(this.artistName, this.band);
			permission = artist.addArtistWithPerm(a);
		}
		// Refresh artistsList
		artistsList = artist.getArtists();
		// clear the add fields
		artistName = "";
	}

	// Getters & setters
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

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

	public Set<Album> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(Set<Album> albumsList) {
		this.albumsList = albumsList;
	}


} 

