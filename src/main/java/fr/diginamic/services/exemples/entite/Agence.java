/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "AGENCE")
public class Agence {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "CA")
	private Double chiffreDaffaire;
	
	@Column(name = "CHARGES")
	private Double charges;

	@OneToMany(mappedBy = "agence")
	protected Set<Vehicule> vehicules = new HashSet<Vehicule>();
	
	public Agence() {
		
	}

	public Agence(String nom, Double chiffreDaffaire, Double charges) {
		this.nom = nom;
		this.chiffreDaffaire = chiffreDaffaire;
		this.charges = charges;
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

	public Double getChiffreDaffaire() {
		return chiffreDaffaire;
	}

	public void setChiffreDaffaire(Double chiffreDaffaire) {
		this.chiffreDaffaire = chiffreDaffaire;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Set<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(Set<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

}
