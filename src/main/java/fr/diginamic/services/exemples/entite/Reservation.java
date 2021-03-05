/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "RESERVATION")
public class Reservation {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE")
	private Vehicule vehicule;
	
	@Column(name = "STATUT_TERMINEE")
	private Boolean statutTerminee;
	
	@Column(name = "DATE_DEBUT")
	private Date dateDebut;
	
	@Column(name = "KM_DEBUT")
	private Integer kmDebut;
	
	@Column(name = "DATE_FIN_PREVUE")
	private Date dateFinPrevue;
	
	@Column(name = "DATE_FIN_REELLE")
	private Date dateFinReelle;
	
	@Column(name = "KM_FIN")
	private Integer kmFin;
	
	@Column(name = "COMMENTAIRE")
	private String commentaire;
	
	@OneToOne
	@JoinColumn(name = "ID_FACTURE")
	private Facture facture;
	
	@Column(name = "TYPE_REGLEMENT")
	@Enumerated(EnumType.STRING)
	private Reglement typeReglement;
	
	public Reservation() {
		
	}

	public Reservation(Client client, Vehicule vehicule, Boolean statutTerminee, Date dateDebut,
			Integer kmDebut, Date dateFinPrevue) {
		this.client = client;
		this.vehicule = vehicule;
		this.statutTerminee = statutTerminee;
		this.dateDebut = dateDebut;
		this.kmDebut = kmDebut;
		this.dateFinPrevue = dateFinPrevue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Boolean getStatutTerminee() {
		return statutTerminee;
	}

	public void setStatutTerminee(Boolean statutTerminee) {
		this.statutTerminee = statutTerminee;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getKmDebut() {
		return kmDebut;
	}

	public void setKmDebut(Integer kmDebut) {
		this.kmDebut = kmDebut;
	}

	public Date getDateFinPrevue() {
		return dateFinPrevue;
	}

	public void setDateFinPrevue(Date dateFinPrevue) {
		this.dateFinPrevue = dateFinPrevue;
	}

	public Date getDateFinReelle() {
		return dateFinReelle;
	}

	public void setDateFinReelle(Date dateFinReelle) {
		this.dateFinReelle = dateFinReelle;
	}

	public Integer getKmFin() {
		return kmFin;
	}

	public void setKmFin(Integer kmFin) {
		this.kmFin = kmFin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Reglement getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(Reglement typeReglement) {
		this.typeReglement = typeReglement;
	}

}
