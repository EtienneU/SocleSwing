/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Agence;

/**
 * @author EtienneUrbano
 *
 */
public class AgenceDao extends AbstractDao {

	private EntityManager em;

	public AgenceDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Agence agence) {
		
		Query query = em.createQuery("SELECT a FROM Agence a WHERE a.nom = ?1");
		query.setParameter(1, agence.getNom());
	    query.setMaxResults(1);
		List<Agence> agenceDB = query.getResultList();
		if (agenceDB == null || agenceDB.isEmpty()) {
			em.persist(agence);
		} else { // Si le agence existe déjà en DB
			// Il faut absolument que l'agence ait un id, sinon erreur "transient instance"
			agence.setId(agenceDB.get(0).getId());
		}
	}

	public List<Agence> findAll() {
		TypedQuery<Agence> query = em.createQuery("SELECT a FROM Agence a", Agence.class);
		return query.getResultList();
	}
	
	public Agence findById(int id) {
		return em.find(Agence.class, id);
	}
	
	public List<Agence> findByContains(String terme) {
		TypedQuery<Agence> query = em.createQuery("SELECT a FROM Agence a WHERE a.nom LIKE ?1", Agence.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(Agence agence) {		
		Agence agenceDB = findById(agence.getId());
		agenceDB.setNom(agence.getNom());
	}

	public void delete(Agence agence) {
		Agence agenceDB = findById(agence.getId());
		em.remove(agenceDB);
	}

}
