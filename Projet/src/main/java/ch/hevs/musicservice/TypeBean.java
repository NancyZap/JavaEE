package ch.hevs.musicservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Type;
import ch.hevs.exception.MusicException;

@Stateful
@RolesAllowed(value = {"visitor", "administrator"})
public class TypeBean implements TypeInterface {

	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "musicPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public List<Type> getTypes() {
		TypedQuery query = em.createQuery("SELECT t FROM Type t", Type.class);
		List<Type> list = query.getResultList();
		return list;
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void addType(Type type) throws MusicException {
		em.persist(type);
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public String addTypeWithPerm(Type type) throws MusicException {
		if(ctx.isCallerInRole("administrator")){
			em.persist(type);
			return "";
		}else{
			return "You are not allowed to add a type!";
		}
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public String addTypeToAlbum(Type type, long albId){
		
		if(ctx.isCallerInRole("administrator")){
			Query query = em.createQuery("SELECT alb from Album alb WHERE alb.id=:id", Album.class);	
			query.setParameter("id", albId);
			
			Album alb = (Album) query.getSingleResult();
			
			alb.addTypes(type);
			return "";
		}else{
			return "You are not allowed to add a type!";
		}
		
	}
	
	public boolean existInAlbum(Type type,long albId) {

		Query query = em.createQuery("SELECT t FROM Album alb, IN (alb.types) t WHERE alb.id=:id");
		query.setParameter("id", albId);
		List<Type> list = query.getResultList();
		
		String s = type.getDescription();
		
		for (Type type1 : list) {
			if(type1.getDescription().equals(s)){
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public boolean exist(String description) {

		TypedQuery query = em.createQuery("SELECT t FROM Type t WHERE t.description=:description", Type.class);
		query.setParameter("description", description);
		try{
			Type type = (Type) query.getSingleResult();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<String> getTypesByAlbum(long albId) {
		
		Query query = em.createQuery("SELECT alb FROM Album alb WHERE alb.id=:id");
		query.setParameter("id", albId);
		
		Album album = (Album) query.getSingleResult();
		
		Set<Type> typesList = album.getTypes();
		List<String> list = new ArrayList<String>();
		
		for (Type type : typesList) {
			list.add(type.getDescription());
		}
		
		return list;
	}

}
