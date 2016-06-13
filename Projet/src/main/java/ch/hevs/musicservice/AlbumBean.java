package ch.hevs.musicservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Album;

@Stateful
public class AlbumBean implements AlbumInterface {

	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "musicPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public List<Album> showAlbumsByArtist(long id_artist) {
		Query query = em.createQuery("SELECT alb FROM Artist art, IN(art.albums) alb where art.id=:id");
		query.setParameter("id", id_artist);
		
		List<Album> listAlbumsByArtist = (List<Album>) query.getResultList();
		
		
		return listAlbumsByArtist;
	}
	
	

}
