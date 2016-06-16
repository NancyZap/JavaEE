package ch.hevs.businessobject;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Album")
public class Album {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="titre")
	private String title;
	@Column(name="annee")
	private int year;


	// id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

    // title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	// year
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}


	//constructors
	public Album() {
	}
	public Album(String title, int year) {
		this.title = title;
		this.year = year;
		this.songs = new HashSet<Song>();
		this.artists = new HashSet<Artist>();
		this.types = new HashSet<Type>();
	}
	
	
	
	// relations
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Song> songs;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Artist> artists;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Type> types;
		
	// types (From Type)
	public Set<Type> getTypes() {
		return types;
	}
	public void setTypes(Set<Type> types) {
		this.types = types;
	}
	
	//artists (From Artist)	
	public Set<Artist> getArtists() {
			return artists;
		}
	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}
		
	//songs (From Song) 
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	
	// helper methods
	public void addArtists(Artist artist) {
		artists.add(artist);
	}
	
	public void addSongs(Song song) {
		songs.add(song);
		song.addAlbums(this);
	}
	
	public void addTypes(Type type) {
		types.add(type);
		type.addAlbums(this);
		
	}

}
