/**
 * 
 */
package fr.diginamic.services.exemples;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.services.exemples.dao.AbstractDao;
import fr.diginamic.services.exemples.dao.ClientDao;
import fr.diginamic.services.exemples.dao.MarqueDao;
import fr.diginamic.services.exemples.dao.ModeleDao;
import fr.diginamic.services.exemples.dao.PermisDao;
import fr.diginamic.services.exemples.entite.Adresse;
import fr.diginamic.services.exemples.entite.Agence;
import fr.diginamic.services.exemples.entite.Client;
import fr.diginamic.services.exemples.entite.Marque;
import fr.diginamic.services.exemples.entite.Modele;
import fr.diginamic.services.exemples.entite.PermisConduire;


/**
 * @author EtienneUrbano
 *
 */
public class TestConnexion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");		
		EntityManager em = AbstractDao.emf.createEntityManager();
		
		EntityTransaction transac = em.getTransaction();
		
		Agence vipLocation 		= new Agence();
		Adresse adresse1 		= new Adresse(14, "route du Moulin", 69005, "Lyon");
		Client client1 			= new Client("Moustier", "Celine", adresse1, "0945675432", "c.moustier@zig.com");
		PermisConduire permis1 	= new PermisConduire("B", "Ed35288647", new Date(110, 5, 6));
		Marque marque1 			= new Marque("Alfa_Romeo");
		Modele modele1 			= new Modele("Giulia", marque1);
		
		transac.begin();
		
		PermisDao permisDao = new PermisDao(em);
		permisDao.insert(permis1);
		
		ClientDao clientDao = new ClientDao(em);
		client1.setPermisDeConduire(permis1);
		clientDao.insert(client1);
		
		MarqueDao marqueDao = new MarqueDao(em);
		marqueDao.insert(marque1);
		
		ModeleDao modeleDao = new ModeleDao(em);
		modeleDao.insert(modele1);
		
		
		
		transac.commit();
	}

}
