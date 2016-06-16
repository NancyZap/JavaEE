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
	
	private String title;
	
	private String albumName;
	private long albumId;
	
	private Set<Song> songsList;
	private SongInterface song;
	
	private String permission;


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
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			albumName =  params.get("albumN"); 
			albumId = Long.valueOf(params.get("albumId")).longValue();
			return "yes";
		}
		catch(Exception e)
		{
			return "no";
		}
	}

	// Add song	
	public void addSong(long idAlbum) {
		if(!song.exist(this.title)){
			Song s = new Song(this.title);
			permission = song.addSongWithPerm(s, idAlbum);
		}
		songsList = song.showSongsByAlbum(idAlbum);
	}
	
	// Delete a song
		public void deleteSong(long id_song)
		{
			//FacesContext fc = FacesContext.getCurrentInstance();
			//Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			//albumId = Long.valueOf(params.get("albumId")).longValue();
			
			//albumId = 20;
			song.deleteSong(id_song);

			//songsList = song.showSongsByAlbum(albumId);
			this.showAlbumSongs(albumId);
		}

	// Getters & setters	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Set<Song> getSongsList() {
		return songsList;
	}

	public void setSongsList(Set<Song> songsList) {
		this.songsList = songsList;
	}

	public SongInterface getSong() {
		return song;
	}

	public void setSong(SongInterface song) {
		this.song = song;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

	//TODO: EVERYTHING ELSE

} 

