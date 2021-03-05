/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Maintenance;

/**
 * @author EtienneUrbano
 *
 */
public class MaintenanceDao extends AbstractDao {

	private EntityManager em;

	public MaintenanceDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Maintenance maintenance) {
		em.persist(maintenance);
	}

	public List<Maintenance> findAll() {
		TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m", Maintenance.class);
		return query.getResultList();
	}
	
	public Maintenance findById(int id) {
		return em.find(Maintenance.class, id);
	}
	

	public void updateCout(Maintenance maintenance) {		
		Maintenance maintenanceDB = findById(maintenance.getId());
		maintenanceDB.setCout(maintenance.getCout());
	}

	public void delete(Maintenance maintenance) {
		Maintenance maintenanceDB = findById(maintenance.getId());
		em.remove(maintenanceDB);
	}

}
