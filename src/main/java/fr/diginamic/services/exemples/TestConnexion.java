/**
 * 
 */
package fr.diginamic.services.exemples;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		EntityManager em = emf.createEntityManager();
	}

}
