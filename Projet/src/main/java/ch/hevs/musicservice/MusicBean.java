package ch.hevs.musicservice;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.hevs.businessobject.Artist;

@Stateful
public class MusicBean implements MusicInterface {
	
	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "test", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public List<Artist> getArtists() {
		TypedQuery query = em.createQuery("SELECT a FROM Artist a", Artist.class);
		List<Artist> list = query.getResultList();
		return list;
	}
	
}
