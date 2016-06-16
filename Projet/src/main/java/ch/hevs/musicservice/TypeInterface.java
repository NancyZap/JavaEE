package ch.hevs.musicservice;

import java.util.List;

import ch.hevs.businessobject.Type;

public interface TypeInterface {

	List<Type> getTypes();
	
	// Ajouter un type (genre musical)
	public void addType(Type type);
	
	public boolean exist(String description);
	
	public boolean existInAlbum(Type t, long albId);

	public String addTypeToAlbum(Type type, long albId);

	public List<String> getTypesByAlbum(long albId);
}
