package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
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
	private List<Album> albumsList;

	private Set<Song> songsList;

	private AlbumInterface album;

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

	
	
	// TODO: Méthode à supprimer si pas utilisée
	/*
	// Show the albums of an artist
	public String showArtistAlbums(long id_artist)
	{
		try
		{
			albumsList = album.showAlbumsByArtist(id_artist);
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}
	 */

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
		album.addAlbum(a, idArtist);}
	}

	// Getters & setters

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

	public List<Album> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(List<Album> albumsList) {
		this.albumsList = albumsList;
	}

	public AlbumInterface getAlbum() {
		return album;
	}

	public void setAlbum(AlbumInterface album) {
		this.album = album;
	}


} 

