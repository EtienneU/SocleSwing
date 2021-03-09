/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Vehicule;

/**
 * @author EtienneUrbano
 *
 */
public class VehiculeDao extends AbstractDao {

	private EntityManager em;

	public VehiculeDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Vehicule vehicule) {
		
		Query query = em.createQuery("SELECT v FROM Vehicule v WHERE v.immatriculation = ?1");
		query.setParameter(1, vehicule.getImmatriculation());
	    query.setMaxResults(1);
		List<Vehicule> vehiculeDB = query.getResultList();
		if (vehiculeDB == null || vehiculeDB.isEmpty()) {
			em.persist(vehicule);
		} else { // Si le vehicule existe déjà en DB
			// Il faut absolument que le vehicule ait un id, sinon erreur "transient instance"
			vehicule.setId(vehiculeDB.get(0).getId());
		}
	}

	public List<Vehicule> findAll() {
		TypedQuery<Vehicule> query = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);
		return query.getResultList();
	}
	
	public Vehicule findById(int id) {
		return em.find(Vehicule.class, id);
	}
	
	public List<Vehicule> findByContains(String terme) {
		TypedQuery<Vehicule> query = em.createQuery("SELECT v FROM Vehicule v WHERE v.marque LIKE ?1 OR v.modele LIKE ?1 OR v.immatriculation LIKE ?1", Vehicule.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateImmatriculation(Vehicule vehicule) {		
		Vehicule vehiculeDB = findById(vehicule.getId());
		vehiculeDB.setImmatriculation(vehicule.getImmatriculation());
	}

	public void delete(Vehicule vehicule) {
		Vehicule vehiculeDB = findById(vehicule.getId());
		em.remove(vehiculeDB);
	}

}
