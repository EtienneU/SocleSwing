/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Client;

/**
 * @author EtienneUrbano
 *
 */
public class ClientDao extends AbstractDao {

	private EntityManager em;

	public ClientDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Client client) {
		
		Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = ?1");
		query.setParameter(1, client.getEmail());
	    query.setMaxResults(1);
		List<Client> clientDB = query.getResultList();
		if (clientDB == null || clientDB.isEmpty()) {
			em.persist(client);
		} else { // Si le client existe déjà en DB
			// Il faut absolument que le client ait un id, sinon erreur "transient instance"
			client.setId(clientDB.get(0).getId());
		}
	}

	public List<Client> findAll() {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}
	
	public Client findById(int id) {
		return em.find(Client.class, id);
	}
	
	public List<Client> findByContains(String terme) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.nom LIKE ?1 OR c.prenom LIKE ?1", Client.class);
		query.setParameter(1, "%" + terme + "%");
		return query.getResultList();
	}

	public void updateNom(Client client, String nom) {		
		Client clientDB = findById(client.getId());
		clientDB.setNom(nom);
	}

	public void delete(Client client) {
		Client clientDB = findById(client.getId());
		em.remove(clientDB);
	}

}
