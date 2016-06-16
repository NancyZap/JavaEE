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
import ch.hevs.businessobject.Song;
import ch.hevs.musicservice.AlbumInterface;


/**
 * AlbumManagedBean.java
 * 
 */

public class AlbumManagedBean
{

	private String title;
	private int year; 

	// TODO: Supprimer si pas utilisé
	//private List<Album> albumsList;
	
	private String artistName;
	private long artistId;
	
	private Set<Album> albumsList;

	private Set<Song> songsList;

	private AlbumInterface album;
	private String permission;


	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		album = (AlbumInterface) ctx.lookup("java:global/projet-0.0.1-SNAPSHOT/AlbumBean!ch.hevs.musicservice.AlbumInterface");    	
	}

	// Show the songs of an album
	public String showAlbumSongs(long id_album)
	{
		try
		{
			songsList = album.showAlbumSongs(id_album);
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}
	
	// Show the albums of an artist
	public String showArtistAlbums(long id_artist)
	{
		try
		{
			albumsList = album.showAlbumsByArtist(id_artist);
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			artistName =  params.get("artistN"); 
			artistId = Long.valueOf(params.get("artistId")).longValue();
			
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}
	

	public Set<Song> getSongsList() {
		return songsList;
	}

	public void setSongsList(Set<Song> songsList) {
		this.songsList = songsList;
	}

	// Add album
	public void addAlbum(long idArtist) {
		if(!album.exist(this.title)){
			Album a = new Album(this.title, this.year);
			permission = album.addAlbumWithPerm(a, idArtist);
		}
		albumsList = album.showAlbumsByArtist(idArtist);
	}

	// Getters & setters
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Album> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(Set<Album> albumsList) {
		this.albumsList = albumsList;
	}

	public AlbumInterface getAlbum() {
		return album;
	}

	public void setAlbum(AlbumInterface album) {
		this.album = album;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}
	
	


} 

