/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.TypeCamion;
import fr.diginamic.services.exemples.entite.TypeVoiture;

/**
 * @author EtienneUrbano
 *
 */
public class TypeVoitureDao extends AbstractDao {

	private EntityManager em;

	public TypeVoitureDao(EntityManager em) {
		this.em = em;
	}

	public void insert(TypeVoiture typeVoiture) {
		
		Query query = em.createQuery("SELECT t FROM TypeVoiture t WHERE t.nom = ?1");
		query.setParameter(1, typeVoiture.getNom());
	    query.setMaxResults(1);
		List<TypeVoiture> typeVoitureDB = query.getResultList();
		if (typeVoitureDB == null || typeVoitureDB.isEmpty()) {
			em.persist(typeVoiture);
		} else { // Si le typeTypeVoiture existe déjà en DB
			// Il faut absolument que le typeTypeVoiture ait un id, sinon erreur "transient instance"
			typeVoiture.setId(typeVoitureDB.get(0).getId());
		}
	}

	public List<TypeVoiture> findAll() {
		TypedQuery<TypeVoiture> query = em.createQuery("SELECT t FROM TypeVoiture t", TypeVoiture.class);
		return query.getResultList();
	}
	
	public TypeVoiture findById(int id) {
		return em.find(TypeVoiture.class, id);
	}
	
	public TypeVoiture findByNom(String nom) {
		TypedQuery<TypeVoiture> query = em.createQuery("SELECT tv FROM TypeVoiture tv WHERE tv.nom LIKE ?1", TypeVoiture.class);
		query.setParameter(1, "%" + nom + "%");
		List<TypeVoiture> result = query.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} 
		return null;
	}
	
	public List<TypeVoiture> findByContains(String terme) {
		TypedQuery<TypeVoiture> query = em.createQuery("SELECT t FROM TypeVoiture t WHERE t.nom LIKE ?1", TypeVoiture.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(TypeVoiture typeVoiture) {		
		TypeVoiture typeVoitureDB = findById(typeVoiture.getId());
		typeVoitureDB.setNom(typeVoiture.getNom());
	}

	public void delete(TypeVoiture typeVoiture) {
		TypeVoiture typeVoitureDB = findById(typeVoiture.getId());
		em.remove(typeVoitureDB);
	}

}
