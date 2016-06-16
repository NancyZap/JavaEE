package ch.hevs.musicservice;

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
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;


@Stateful
@RolesAllowed(value = {"visitor", "administrator"})
public class AlbumBean implements AlbumInterface {

	@Resource
	private SessionContext ctx;
	@PersistenceContext(name = "musicPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public Set<Album> showAlbumsByArtist(long id_artist) {
		Query query = em.createQuery("SELECT art FROM Artist art WHERE art.id=:id");
		query.setParameter("id", id_artist);

		Artist artist = (Artist) query.getSingleResult();
		
		Set<Album> albumsList = artist.getAlbums();
		
		return albumsList;
	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void addAlbum(Album album, long idArtist) {
		Query query = em.createQuery("SELECT a from Artist a WHERE a.id=:id", Artist.class);	
		query.setParameter("id", idArtist);
		
		Artist art = (Artist) query.getSingleResult();
		
		art.addAlbums(album);
		
		em.persist(album);
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public String addAlbumWithPerm(Album album, long idArtist) {
		if(ctx.isCallerInRole("administrator")){
			Query query = em.createQuery("SELECT a from Artist a WHERE a.id=:id", Artist.class);	
			query.setParameter("id", idArtist);
			
			Artist art = (Artist) query.getSingleResult();
			
			art.addAlbums(album);
			
			em.persist(album);
			return "";	
		}else{
			return "You are not allowed to add an album!";
		}
		
	}

	@Override
	public Set<Song> showAlbumSongs(long id_album) {
		Query query = em.createQuery("SELECT alb FROM Album alb where alb.id=:id");
		query.setParameter("id", id_album);
		
		Album album = (Album) query.getSingleResult();
		
		Set<Song> songsList = album.getSongs();
		
		return songsList;
	}

	@Override
	public boolean exist(String title) {
		TypedQuery query = em.createQuery("SELECT alb FROM Album alb WHERE alb.title=:title", Album.class);
		query.setParameter("title", title);
		try{
			Album album = (Album) query.getSingleResult();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	

}
