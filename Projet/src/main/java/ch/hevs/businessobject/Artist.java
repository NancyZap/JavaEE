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
@Table(name="Artiste")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="prenom")
	private String name;
	@Column(name="nom")
	private String lastname;
	@Column(name="groupe")
	private boolean band;

	// id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	// name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// lastname
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	// band
	public boolean getBand() {
		return band;
	}
	public void setBand(boolean band) {
		this.band = band;
	}
	
	//constructors
	public Artist() {
	}
	public Artist(String name, String lastname, boolean band) {
		this.name = name;
		this.lastname = lastname;
		this.band = band;
		albums = new HashSet<Album>();
	}
	public Artist(String name, boolean band) {
		this.name = name;
		this.band = band; 
		albums = new HashSet<Album>();
	}

	
	// relations
	@ManyToMany(mappedBy = "artists", cascade = CascadeType.ALL)
	private Set<Album> albums;

	// albums (From Album) 
	public Set<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	// helper methods
	public void addAlbums(Album a) {
		albums.add(a);
		a.addArtists(this);
	}

	
	
}
