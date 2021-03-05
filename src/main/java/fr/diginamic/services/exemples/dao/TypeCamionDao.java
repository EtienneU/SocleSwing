/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.TypeCamion;

/**
 * @author EtienneUrbano
 *
 */
public class TypeCamionDao extends AbstractDao {

	private EntityManager em;

	public TypeCamionDao(EntityManager em) {
		this.em = em;
	}

	public void insert(TypeCamion typeCamion) {
		
		Query query = em.createQuery("SELECT t FROM TypeCamion t WHERE t.nom = ?1");
		query.setParameter(1, typeCamion.getNom());
	    query.setMaxResults(1);
		List<TypeCamion> typeCamionDB = query.getResultList();
		if (typeCamionDB == null || typeCamionDB.isEmpty()) {
			em.persist(typeCamion);
		} else { // Si le typeTypeCamion existe déjà en DB
			// Il faut absolument que le typeTypeCamion ait un id, sinon erreur "transient instance"
			typeCamion.setId(typeCamionDB.get(0).getId());
		}
	}

	public List<TypeCamion> findAll() {
		TypedQuery<TypeCamion> query = em.createQuery("SELECT t FROM TypeCamion t", TypeCamion.class);
		return query.getResultList();
	}
	
	public TypeCamion findById(int id) {
		return em.find(TypeCamion.class, id);
	}
	
	public List<TypeCamion> findByContains(String terme) {
		TypedQuery<TypeCamion> query = em.createQuery("SELECT t FROM TypeCamion t WHERE t.nom LIKE ?1", TypeCamion.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(TypeCamion typeCamion) {		
		TypeCamion typeCamionDB = findById(typeCamion.getId());
		typeCamionDB.setNom(typeCamion.getNom());
	}

	public void delete(TypeCamion typeCamion) {
		TypeCamion typeCamionDB = findById(typeCamion.getId());
		em.remove(typeCamionDB);
	}

}
