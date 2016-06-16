package ch.hevs.musicservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Song;

@Stateful
public class SongBean implements SongInterface {

	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "musicPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	/* A supprimer si plus utilisée
	@Override
	public List<Song> showSongsByAlbum(long id_album) {
		
		Query query = em.createQuery("SELECT son FROM Album alb, IN(alb.songs) son where alb.id=:id");
		query.setParameter("id", id_album);
		
		List<Song> listSongsByAlbum = (List<Song>) query.getResultList();
		
		return listSongsByAlbum;
	}
	
	*/
	
	@Override
	public Set<Song> showSongsByAlbum(long id_album) {
		
		Query query = em.createQuery("SELECT alb FROM Album alb WHERE alb.id=:id");
		query.setParameter("id", id_album);
		
		Album album = (Album) query.getSingleResult();
		
		Set<Song> songsList = album.getSongs();
		
		return songsList;
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void addSong(Song song, long idAlbum) {
		Query query = em.createQuery("SELECT a FROM Album a WHERE a.id=:id", Album.class);
		query.setParameter("id", idAlbum);
		
		Album alb = (Album) query.getSingleResult();
		
		em.persist(song);
		
	}

	@Override
	public boolean exist(String title) {
		TypedQuery query = em.createQuery("SELECT son FROM Song son WHERE son.title=:title", Song.class);
		query.setParameter("title", title);
		try {
			Song song = (Song) query.getSingleResult();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	//TODO: EVERYTHING ELSE

}
