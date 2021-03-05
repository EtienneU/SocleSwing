/**
 * 
 */
package fr.diginamic.services.exemples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.services.exemples.entite.Reservation;

/**
 * @author EtienneUrbano
 *
 */
public class ReservationDao extends AbstractDao {

	private EntityManager em;

	public ReservationDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Reservation reservation) {
		em.persist(reservation);
	}

	public List<Reservation> findAll() {
		TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r", Reservation.class);
		return query.getResultList();
	}
	
	public Reservation findById(int id) {
		return em.find(Reservation.class, id);
	}

	public void delete(Reservation reservation) {
		Reservation reservationDB = findById(reservation.getId());
		em.remove(reservationDB);
	}

}
