package ch.hevs.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Type;


public class PersistenceTest {

	@Test
	public void test() {
		EntityTransaction tx = null;

		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Artist a1 = new Artist("Eminem", false);
			Artist a2 = new Artist("Twenty One Pilots", true);
			Artist a3 = new Artist ("Lana", "Del Rey", false);
			
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

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}



	}
}









/*
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();


			Client c1 = new Client("Platini", "Michel", "former football player");
			Address a1 = new Address("75000", "Champs elyse", "Paris");
			c1.setAddress(a1);

			c1.addExternalAccount(new ExternalAccount("010101", "Account A"));
			c1.addExternalAccount(new ExternalAccount("020202", "Account B"));

			InternalAccount ci1 = new InternalAccount("1", "desc1", 1000);
			InternalAccount ci2 = new InternalAccount("2", "desc2", 2000);

			ci1.addOperation(new Operation("Boucher", 10, new Date()));
			ci1.addOperation(new Operation("Charcutier", 12, new Date()));

			ci2.addOperation(new Operation("Plasma", 10000, new Date()));
			ci2.addOperation(new Operation("Ipod", 450, new Date()));
			ci2.addOperation(new Operation("Nounou", 700, new Date()));

			c1.addInternalAccount(ci1);
			c1.addInternalAccount(ci2);

			Client c2 = new Client("Parker", "Tony", "Basketeur");
			Address a2 = new Address("XXXX", "Parc privé", "San Antonio");
			c2.setAddress(a2);

			c2.addExternalAccount(new ExternalAccount("88888", "Account de Denis"));
			c2.addExternalAccount(new ExternalAccount("77777",
					"Account epargne en suisse"));

			InternalAccount ci3 = new InternalAccount("3", "desc3", 3000);
			ci3.addOperation(new Operation("Boite de nuit", 10000, new Date()));
			ci3.addOperation(new Operation("Prada", 1000, new Date()));

			c2.addInternalAccount(ci2); // compte partagé avec Michel platini
										// :-)
			c2.addInternalAccount(ci3);

			Agency a = new Agency();
			a.addClient(c1);
			a.addClient(c2);

			Banker b1 = new Banker("Alexandre", "Jardin",
					"alex@hevs.ch");
			Banker b2 = new Banker("Marguerite", "Duras",
					"duras@hevs.ch");

			a.addEmployee(b1);
			a.addEmployee(b2);

			Address a3 = new Address("01", "A coté du lac leman", "Lausanne");
			a.setAddress(a3);

			em.persist(a);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			/*
 * try { tx.rollback(); } catch (IllegalStateException e1) {
 * e1.printStackTrace(); } catch (SecurityException e1) {
 * e1.printStackTrace(); } catch (SystemException e1) {
 * e1.printStackTrace(); }

		}
 */


