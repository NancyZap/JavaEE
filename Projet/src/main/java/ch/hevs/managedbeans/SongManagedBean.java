package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.musicservice.AlbumInterface;
import ch.hevs.musicservice.ArtistInterface;
import ch.hevs.musicservice.SongInterface;

/**
 * SongManagedBean.java
 * 
 */

public class SongManagedBean
{
	private List<Song> songsList;

	private SongInterface song;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		song = (SongInterface) ctx.lookup("java:global/projet-0.0.1-SNAPSHOT/SongBean!ch.hevs.musicservice.SongInterface");    	

	}

	// Show the songs of an album
	public String showAlbumSongs(long id_album)
	{
		try
		{
			songsList = song.showSongsByAlbum(id_album);
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}

	// Add song
	// TODO: faire la méthode d'ajout de songs
	public String addSong() {
		return null;
	}

	// Getters & setters
	
	public List<Song> getSongsList() {
		return songsList;
	}

	public void setSongsList(List<Song> songsList) {
		this.songsList = songsList;
	}

	public SongInterface getSong() {
		return song;
	}

	public void setSong(SongInterface song) {
		this.song = song;
	}

	//TODO: EVERYTHING ELSE

} 

