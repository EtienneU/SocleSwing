/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Marque;

/**
 * @author EtienneUrbano
 *
 */
public class MarqueDao extends AbstractDao {

	private EntityManager em;

	public MarqueDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Marque marque) {
		
		Query query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = ?1");
		query.setParameter(1, marque.getNom());
	    query.setMaxResults(1);
		List<Marque> marqueDB = query.getResultList();
		if (marqueDB == null || marqueDB.isEmpty()) {
			em.persist(marque);
		} else { // Si le marque existe déjà en DB
			// Il faut absolument que le marque ait un id, sinon erreur "transient instance"
			marque.setId(marqueDB.get(0).getId());
		}
	}

	public List<Marque> findAll() {
		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m", Marque.class);
		return query.getResultList();
	}
	
	public Marque findById(int id) {
		return em.find(Marque.class, id);
	}
	
	public List<Marque> findByContains(String terme) {
		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.nom LIKE ?1", Marque.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(Marque marque) {		
		Marque marqueDB = findById(marque.getId());
		marqueDB.setNom(marque.getNom());
	}

	public void delete(Marque marque) {
		Marque marqueDB = findById(marque.getId());
		em.remove(marqueDB);
	}

}
