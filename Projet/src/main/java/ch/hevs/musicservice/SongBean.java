package ch.hevs.musicservice;

import java.util.ArrayList;
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

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Song;

@Stateful
public class SongBean implements SongInterface {

	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "musicPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public List<Song> showSongsByAlbum(long id_album) {
		
		Query query = em.createQuery("SELECT son FROM Album alb, IN(alb.songs) son where alb.id=:id");
		query.setParameter("id", id_album);
		
		List<Song> listSongsByAlbum = (List<Song>) query.getResultList();
		
		return listSongsByAlbum;
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void addSong(Song song) {
		em.persist(song);
		
	}
	
	//TODO: EVERYTHING ELSE

}
