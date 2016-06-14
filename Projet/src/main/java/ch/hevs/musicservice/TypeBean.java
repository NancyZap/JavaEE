package ch.hevs.musicservice;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import ch.hevs.businessobject.Type;

@Stateful
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

}
