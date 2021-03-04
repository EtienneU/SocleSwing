/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author EtienneUrbano
 *
 */
public class AbstractDao {

	// Creation de l'entity Manager via le peristence unit "pu-location-voiture" du
	// fichier persistence.xml
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");

}
