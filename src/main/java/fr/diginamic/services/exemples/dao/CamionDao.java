/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Camion;

/**
 * @author EtienneUrbano
 *
 */
public class CamionDao extends AbstractDao {

	private EntityManager em;

	public CamionDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Camion camion) {
		
		Query query = em.createQuery("SELECT c FROM Camion c WHERE c.immatriculation = ?1");
		query.setParameter(1, camion.getImmatriculation());
	    query.setMaxResults(1);
		List<Camion> camionDB = query.getResultList();
		if (camionDB == null || camionDB.isEmpty()) {
			em.persist(camion);
		} else { // Si le camion existe déjà en DB
			// Il faut absolument que le camion ait un id, sinon erreur "transient instance"
			camion.setId(camionDB.get(0).getId());
		}
	}

	public List<Camion> findAll() {
		TypedQuery<Camion> query = em.createQuery("SELECT c FROM Camion c", Camion.class);
		return query.getResultList();
	}
	
	public Camion findById(int id) {
		return em.find(Camion.class, id);
	}
	
	public List<Camion> findByContains(String terme) {
		TypedQuery<Camion> query = em.createQuery("SELECT c FROM Camion c WHERE c.marque LIKE ?1 OR c.modele LIKE ?1 OR c.immatriculation LIKE ?1", Camion.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateImmatriculation(Camion camion) {		
		Camion camionDB = findById(camion.getId());
		camionDB.setImmatriculation(camion.getImmatriculation());
	}

	public void delete(Camion camion) {
		Camion camionDB = findById(camion.getId());
		em.remove(camionDB);
	}

}
