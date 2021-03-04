/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Client;
import fr.diginamic.services.exemples.entite.PermisConduire;

/**
 * @author EtienneUrbano
 *
 */
public class PermisDao extends AbstractDao {

	private EntityManager em;

	public PermisDao(EntityManager em) {
		this.em = em;
	}

	public void insert(PermisConduire permis) {
		
		Query query = em.createQuery("SELECT permis FROM PermisConduire permis WHERE permis.numero = ?1");
		query.setParameter(1, permis.getNumero());
	    query.setMaxResults(1);
		List<PermisConduire> permisDB = query.getResultList();
		if (permisDB == null || permisDB.isEmpty()) {
			em.persist(permis);
		} else { // Si le client existe déjà en DB
			// Il faut absolument que le client ait un id, sinon erreur "transient instance"
			permis.setId(permisDB.get(0).getId());
		}
	}

	public List<PermisConduire> findAll() {
		TypedQuery<PermisConduire> query = em.createQuery("SELECT p FROM PermisConduire p", PermisConduire.class);
		return query.getResultList();
	}
	
	public PermisConduire findById(int id) {
		return em.find(PermisConduire.class, id);
	}

	public void updateNumero(PermisConduire permis) {		
		PermisConduire permisDB = findById(permis.getId());
		permisDB.setNumero(permis.getNumero());
	}

	public void delete(PermisConduire permis) {
		PermisConduire permisDB = findById(permis.getId());
		em.remove(permisDB);
	}

}
