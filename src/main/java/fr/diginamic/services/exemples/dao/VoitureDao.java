/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Voiture;

/**
 * @author EtienneUrbano
 *
 */
public class VoitureDao extends AbstractDao {

	private EntityManager em;

	public VoitureDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Voiture voiture) {
		
		Query query = em.createQuery("SELECT v FROM Voiture v WHERE v.immatriculation = ?1");
		query.setParameter(1, voiture.getImmatriculation());
	    query.setMaxResults(1);
		List<Voiture> voitureDB = query.getResultList();
		if (voitureDB == null || voitureDB.isEmpty()) {
			em.persist(voiture);
		} else { // Si le voiture existe déjà en DB
			// Il faut absolument que le voiture ait un id, sinon erreur "transient instance"
			voiture.setId(voitureDB.get(0).getId());
		}
	}

	public List<Voiture> findAll() {
		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v", Voiture.class);
		return query.getResultList();
	}
	
	public Voiture findById(int id) {
		return em.find(Voiture.class, id);
	}
	
	public List<Voiture> findByContains(String terme) {
		TypedQuery<Voiture> query = em.createQuery("SELECT v FROM Voiture v WHERE v.marque LIKE ?1 OR v.modele LIKE ?1 OR v.immatriculation LIKE ?1", Voiture.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateImmatriculation(Voiture voiture) {		
		Voiture voitureDB = findById(voiture.getId());
		voitureDB.setImmatriculation(voiture.getImmatriculation());
	}

	public void delete(Voiture voiture) {
		Voiture voitureDB = findById(voiture.getId());
		em.remove(voitureDB);
	}

}
