/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name="CLIENT")
public class Client implements Selectable {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOM", nullable = false)
	private String nom;
	
	@Column(name = "PRENOM", nullable = false)
	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
	@Column(name = "NUM_TEL", nullable = false, unique = true)
	private String numTel;
	
	@Column(name = "E_MAIL", nullable = false)
	private String email;
	
	@OneToOne
	@JoinColumn(name = "ID_PERMIS")
	private PermisConduire permisDeConduire;
	
	@OneToMany(mappedBy = "client")
	private Set <Facture> factures = new HashSet<Facture>();
	
	@OneToMany(mappedBy = "client")
	private Set <Reservation> reservations = new HashSet<Reservation>();
	
	public Client() {
	}

	public Client(String nom, String prenom, String numTel, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.numTel = numTel;
		this.email = email;
	}
	
	public Client(String nom, String prenom, Adresse adresse, String numTel, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.numTel = numTel;
		this.email = email;
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return nom.toUpperCase() + " " + prenom + " - "
				+ numTel + " - " + email + " - " + adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PermisConduire getPermisDeConduire() {
		return permisDeConduire;
	}

	public void setPermisDeConduire(PermisConduire permisDeConduire) {
		this.permisDeConduire = permisDeConduire;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
