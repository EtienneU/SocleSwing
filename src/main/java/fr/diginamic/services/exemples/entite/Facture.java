/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "FACTURE")
public class Facture {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "RECAP_RESA")
	private String recapResa;
	
	@Column(name = "NUMERO", nullable = false, unique = true)
	private String numero;
	
	@Column(name = "MONTANT", nullable = false)
	private Double montant;
	
	@Column(name = "STATUT_PAYE", nullable = false)
	private Boolean statutPaye;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	private Client client;
	
	@OneToOne
	@JoinColumn(name = "ID_RESERVATION", nullable = false)
	private Reservation reservation;	
	
	public Facture() {
	}

	public Facture(String numero, Double montant, Boolean statutPaye, 
			Client client, Reservation reservation) {
		this.numero = numero;
		this.montant = montant;
		this.statutPaye = statutPaye;
		this.client = client;
		this.reservation = reservation;
		this.recapResa = client.getNom().toUpperCase() + " " + client.getPrenom() 
			+ " emprunte le véhicule suivant : " + reservation.getVehicule().getTypeVehicule() 
			+ " immatriculation " + reservation.getVehicule().getImmatriculation();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecapResa() {
		return recapResa;
	}

	public void setRecapResa(String recapResa) {
		this.recapResa = recapResa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Boolean getStatutPaye() {
		return statutPaye;
	}

	public void setStatutPaye(Boolean statutPaye) {
		this.statutPaye = statutPaye;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
