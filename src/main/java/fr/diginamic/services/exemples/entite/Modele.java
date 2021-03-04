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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "MODELE")
public class Modele {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOM")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "ID_MARQUE")
	private Marque marque;
	
	@OneToMany(mappedBy = "modele")
	private Set<Vehicule> vehicules = new HashSet<Vehicule>();
	
	public Modele() {
		
	}

	public Modele(String nom, Marque marque, Set<Vehicule> vehicules) {
		this.nom = nom;
		this.marque = marque;
		this.vehicules = vehicules;
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

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Set<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(Set<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

}

