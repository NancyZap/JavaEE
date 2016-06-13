package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.musicservice.AlbumInterface;
import ch.hevs.musicservice.ArtistInterface;

/**
 * AlbumManagedBean.java
 * 
 */

public class AlbumManagedBean
{

	private List<Album> albumsList;

	private AlbumInterface album;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		album = (AlbumInterface) ctx.lookup("java:global/projet-0.0.1-SNAPSHOT/AlbumBean!ch.hevs.musicservice.AlbumInterface");    	
		
	}
	
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
		
		// Getters & setters

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

