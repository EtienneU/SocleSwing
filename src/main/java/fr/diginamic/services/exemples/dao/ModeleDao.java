/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Modele;

/**
 * @author EtienneUrbano
 *
 */
public class ModeleDao extends AbstractDao {

	private EntityManager em;

	public ModeleDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Modele modele) {
		
		Query query = em.createQuery("SELECT m FROM Modele m WHERE m.nom = ?1");
		query.setParameter(1, modele.getNom());
	    query.setMaxResults(1);
		List<Modele> modeleDB = query.getResultList();
		if (modeleDB == null || modeleDB.isEmpty()) {
			em.persist(modele);
		} else { // Si le Modele existe déjà en DB
			// Il faut absolument que le Modele ait un id, sinon erreur "transient instance"
			modele.setId(modeleDB.get(0).getId());
		}
	}

	public List<Modele> findAll() {
		TypedQuery<Modele> query = em.createQuery("SELECT m FROM Modele m", Modele.class);
		return query.getResultList();
	}
	
	public Modele findById(int id) {
		return em.find(Modele.class, id);
	}
	
	public Modele findByNom(String nom) {
		TypedQuery<Modele> query = em.createQuery("SELECT m FROM Modele m WHERE m.nom LIKE ?1", Modele.class);
		query.setParameter(1, "%" + nom + "%");
		List<Modele> result = query.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} 
		return null;
	}
	
	public List<Modele> findByContains(String terme) {
		TypedQuery<Modele> query = em.createQuery("SELECT m FROM Modele m WHERE m.nom LIKE ?1", Modele.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(Modele modele) {		
		Modele modeleDB = findById(modele.getId());
		modeleDB.setNom(modele.getNom());
	}

	public void delete(Modele modele) {
		Modele modeleDB = findById(modele.getId());
		em.remove(modeleDB);
	}

}
