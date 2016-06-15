package ch.hevs.musicservice;

import java.util.List;

import ch.hevs.businessobject.Type;

public interface TypeInterface {

	List<Type> getTypes();
	
	// Ajouter un type (genre musical)
	public void addType(Type type);
	
	// Supprimer un type (genre musical)
	public void deleteType(long id_type);
	
	public boolean exist(String description);
}
