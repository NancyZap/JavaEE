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

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Song;


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

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void addAlbum(Album album) {
		em.persist(album);
		
	}

	@Override
	public Set<Song> showAlbumSongs(long id_album) {
		Query query = em.createQuery("SELECT alb FROM Album alb where alb.id=:id");
		query.setParameter("id", id_album);
		
		Album album = (Album) query.getSingleResult();
		
		Set<Song> songsList = album.getSongs();
		
		return songsList;
	}
	
	

}
