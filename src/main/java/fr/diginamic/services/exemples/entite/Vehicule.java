package fr.diginamic.services.exemples.entite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "VEHICULE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicule implements Selectable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_MARQUE")
	protected Marque marque;
	
	@ManyToOne
	@JoinColumn(name = "ID_MODELE")
	protected Modele modele;
	
	@Column(name = "TYPE_VEHICULE")
	@Enumerated(EnumType.STRING)
	protected TypeVehicule typeVehicule;
	
	@Column(name = "IMMATRICULATION")
	protected String immatriculation;
	
	@Column(name = "STATUT")
	@Enumerated(EnumType.STRING)
	protected Statut statut;
	
	@OneToMany(mappedBy = "vehicule")
	protected Set<Maintenance> maintenances = new HashSet<Maintenance>();
	
	@OneToMany(mappedBy = "vehicule")
	protected Set<Reservation> reservations = new HashSet<Reservation>();
	
	@Column(name = "KILOMETRAGE", nullable = false)
	protected Integer kilometrage;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGENCE")
	protected Agence agence;
	
	public Vehicule() {
		
	}
	
	public Vehicule(Marque marque, Modele modele, String immatriculation) {
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
	}

	public Vehicule(Integer id, String immatriculation, Marque marque, Modele modele) {
		this.id = id;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehicule)) {
			return false;
		}
		Vehicule autre = (Vehicule)obj;
		return new EqualsBuilder().append(id, autre.getId()).isEquals();
	}

	@Override
	public String toString() {
		return immatriculation;
	}
	
	/**
	 * @return l'id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id l'id à modifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return l'immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}
	/**
	 * @param immatriculation l'immatriculation à modifier
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	/**
	 * @return la marque
	 */
	public Marque getMarque() {
		return marque;
	}
	/**
	 * @param marque la marque à modifier
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	/**
	 * @return le modele
	 */
	public Modele getModele() {
		return modele;
	}
	/**
	 * @param modele le modele à modifier
	 */
	public void setModele(Modele modele) {
		this.modele = modele;
	}
	/**
	 * @return le statut du véhicule
	 */
	public Statut getStatut() {
		return statut;
	}
	/**
	 * @param statut le statut à modifier
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	/**
	 * @return le Set des maintenances
	 */
	public Set<Maintenance> getMaintenances() {
		return maintenances;
	}
	/**
	 * @param maintenances le Set de maintenances à modifier
	 */
	public void setMaintenances(Set<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
	/**
	 * @return le Set des reservations
	 */
	public Set<Reservation> getReservations() {
		return reservations;
	}
	/**
	 * @param reservations le Set des reservations à modifier
	 */
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	/**
	 * @return le kilometrage
	 */
	public Integer getKilometrage() {
		return kilometrage;
	}
	/**
	 * @param kilometrage le kilometrage à modifier
	 */
	public void setKilometrage(Integer kilometrage) {
		this.kilometrage = kilometrage;
	}
	/**
	 * @return l'agence
	 */
	public Agence getAgence() {
		return agence;
	}
	/**
	 * @param agence l'agence à modifier
	 */
	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public TypeVehicule getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(TypeVehicule typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

}
