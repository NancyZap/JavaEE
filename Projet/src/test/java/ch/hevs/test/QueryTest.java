package ch.hevs.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;


import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Type;

public class QueryTest {

	@Test
	public void test() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String cmd;
			while (true) {
				System.out
						.println("Write a query [or 'populate' or 'quit']: ");
				cmd = reader.readLine();

				if ("populate".equals(cmd)) {
					populate();
				} else if ("quit".equals(cmd)) {
					System.out.println("The End");
					break;
				} else {
					executeRequest(cmd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void executeRequest(String cmd) {
		List result = null;
		EntityTransaction tx = null;
		try {

			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			result = em.createQuery(cmd).getResultList();
			Iterator it = result.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			tx.commit();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} 
		}
	}

	public static void populate() {
		EntityTransaction tx = null;
		try {

			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Artist a1 = new Artist("Eminem", false);
			Artist a2 = new Artist("Twenty One Pilots", true);
			Artist a3 = new Artist ("Lana Del Rey", false);
			
			Album alb1 = new Album("Blurryface", 2015);
			alb1.addSongs(new Song("Stressed Out"));
			alb1.addSongs(new Song("Ride"));
			alb1.addSongs(new Song("Fairly Local"));
			alb1.addSongs(new Song("Tear in My Heart"));
			alb1.addSongs(new Song("Lane Boy"));
			alb1.addSongs(new Song("The Judge"));
			alb1.addSongs(new Song("Doubt"));
			alb1.addSongs(new Song("Polarize"));
			alb1.addSongs(new Song("We Don't Believe What's on TV"));
			alb1.addSongs(new Song("Message Man"));
			alb1.addSongs(new Song("Hometown"));
			alb1.addSongs(new Song("Not Today"));
			alb1.addSongs(new Song("Goner"));
			
			alb1.addTypes(new Type("Hip hop alternatif"));
			alb1.addTypes(new Type("Rock"));
			
			a2.addAlbums(alb1);

			//em.persist(a);
			em.persist(a1);
			em.persist(a2);
			em.persist(a3);
			
			em.persist(alb1);
	
			tx.commit();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
