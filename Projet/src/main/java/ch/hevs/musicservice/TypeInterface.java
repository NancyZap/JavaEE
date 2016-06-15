package ch.hevs.musicservice;

import java.util.List;

import ch.hevs.businessobject.Type;

public interface TypeInterface {

	List<Type> getTypes();
	
	public void deleteType(long id_type);
}
