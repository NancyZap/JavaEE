package ch.hevs.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			
			
			Type t1 = new Type("Metal");
			Type t2 = new Type("Jazz");
			Type t3 = new Type("Blues");
			Type t4 = new Type("Variété");
			Type t5 = new Type("Reggae");
			Type t6 = new Type("Electro");
			Type t7 = new Type("Pop");
			Type t8 = new Type("Soul");
			Type t9 = new Type("Funk");
			Type t10 = new Type("Indie");
			Type t11 = new Type("Rap");
			
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
			
			Album alb2 = new Album("Vessel", 2013);
			alb2.addSongs(new Song("Ode to Sleep"));
			alb2.addSongs(new Song("Holding On to You"));
			alb2.addSongs(new Song("Migraine"));
			alb2.addSongs(new Song("House of Gold"));
			alb2.addSongs(new Song("Car Radio"));
			alb2.addSongs(new Song("Semi-Automatic"));
			alb2.addSongs(new Song("Screen"));
			alb2.addSongs(new Song("The Run and Go"));
			alb2.addSongs(new Song("Fake You Out"));
			alb2.addSongs(new Song("Guns for Hands"));
			alb2.addSongs(new Song("Trees"));
			alb2.addSongs(new Song("Truce"));
	
			alb2.addTypes(t6);
			
			a2.addAlbums(alb2);
			a2.addAlbums(alb1);
			
			Album alb3 = new Album("Ultraviolence", 2014);
			alb3.addSongs(new Song("Cruel World"));
			alb3.addSongs(new Song("Ultraviolence"));
			alb3.addSongs(new Song("Shades of Cool"));
			alb3.addSongs(new Song("Brooklyn Baby"));
			alb3.addSongs(new Song("West Coast"));
			alb3.addSongs(new Song("Sad Girl"));
			alb3.addSongs(new Song("Pretty When You Cry"));
			alb3.addSongs(new Song("Money Power Glory"));
	
			alb3.addTypes(t7);
			alb3.addTypes(t4);
			
			a3.addAlbums(alb3);
			
			Album alb4 = new Album("The Marshall Mathers LP 2", 2013);
			alb4.addSongs(new Song("Bad Guy"));
			alb4.addSongs(new Song("Parking Lot"));
			alb4.addSongs(new Song("Ryhme Or Reason"));
			alb4.addSongs(new Song("So Muche Better"));
			alb4.addSongs(new Song("Survival"));
			alb4.addSongs(new Song("Legacy"));
			alb4.addSongs(new Song("Asshole"));
			alb4.addSongs(new Song("Berzerk"));
			alb4.addSongs(new Song("Rap God"));
			alb4.addSongs(new Song("Brainess"));
			alb4.addSongs(new Song("The Monster"));
			alb4.addSongs(new Song("So Far..."));
			
			alb4.addTypes(t7);
			
			a1.addAlbums(alb4);
			
			em.persist(a1);
			em.persist(a2);
			em.persist(a3);
			
			em.persist(t1);
			em.persist(t2);
			em.persist(t3);
			em.persist(t4);
			em.persist(t5);
			em.persist(t6);
			em.persist(t7);
			em.persist(t8);
			em.persist(t9);
			em.persist(t10);
			em.persist(t11);
			
			em.persist(alb1);
			em.persist(alb2);
			em.persist(alb3);
			em.persist(alb4);
	
			tx.commit();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
