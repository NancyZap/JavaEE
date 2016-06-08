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
@Table(name="Genre")
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="description")
	private String description;
	
	// id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	// description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	//constructors
	public Type() {
	}
	public Type(String description) {
		this.description = description;
		albums = new HashSet<Album>();
	}

	// relations
	@ManyToMany(mappedBy = "types", cascade = CascadeType.ALL)
	private Set<Album> albums;
	
	// albums (From Album)
	public Set<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	//helper methods
	public void addAlbums(Album album) {
		albums.add(album);
	}

}
