/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Facture;

/**
 * @author EtienneUrbano
 *
 */
public class FactureDao extends AbstractDao {

	private EntityManager em;

	public FactureDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Facture facture) {
		
		Query query = em.createQuery("SELECT f FROM Facture f WHERE f.numero = ?1");
		query.setParameter(1, facture.getNumero());
	    query.setMaxResults(1);
		List<Facture> factureDB = query.getResultList();
		if (factureDB == null || factureDB.isEmpty()) {
			em.persist(facture);
		} else { // Si le facture existe déjà en DB
			// Il faut absolument qu'elle ait un id, sinon erreur "transient instance"
			facture.setId(factureDB.get(0).getId());
		}
	}

	public List<Facture> findAll() {
		TypedQuery<Facture> query = em.createQuery("SELECT f FROM Facture f", Facture.class);
		return query.getResultList();
	}
	
	public Facture findById(int id) {
		return em.find(Facture.class, id);
	}
	
	public List<Facture> findByContains(String terme) {
		TypedQuery<Facture> query = em.createQuery("SELECT f FROM Facture f WHERE f.numero LIKE ?1 OR f.recapResa LIKE ?1", Facture.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNumero(Facture facture) {		
		Facture factureDB = findById(facture.getId());
		factureDB.setNumero(facture.getNumero());
	}

	public void delete(Facture facture) {
		Facture factureDB = findById(facture.getId());
		em.remove(factureDB);
	}

}
