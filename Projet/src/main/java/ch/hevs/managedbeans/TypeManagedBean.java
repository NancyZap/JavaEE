package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.util.TypeLiteral;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Type;
import ch.hevs.musicservice.TypeInterface;

public class TypeManagedBean {

	private List<Type> typesList;
	private List<String> typeDescriptions;
	private String typeDescription;
	private TypeInterface type;
	

	@PostConstruct
	public void initialize() throws NamingException {
		

		InitialContext ctx = new InitialContext();
		type = (TypeInterface) ctx.lookup("java:global/projet-0.0.1-SNAPSHOT/TypeBean!ch.hevs.musicservice.TypeInterface");    	

		// get types
		typesList = type.getTypes();
		this.typeDescriptions = new ArrayList<String>();
		for (Type type : typesList) {
			this.typeDescriptions.add(type.getDescription());
		}
	}
	
	// Add a type
	public void addType() {
		if(!type.exist(this.typeDescription)) {
			Type t = new Type(this.typeDescription);
			type.addType(t);
			typesList = type.getTypes();
		}
	}
	
	// Delete a type
	public void deleteType(long id_type)
	{
		type.deleteType(id_type);
		typesList = type.getTypes();
	}


	//Getters & Setters
	public List<Type> getTypesList() {
		return typesList;
	}

	public void setTypesList(List<Type> typesList) {
		this.typesList = typesList;
	}

	public List<String> getTypeDescriptions() {
		return typeDescriptions;
	}

	public void setTypeDescriptions(List<String> typeDescriptions) {
		this.typeDescriptions = typeDescriptions;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
}
