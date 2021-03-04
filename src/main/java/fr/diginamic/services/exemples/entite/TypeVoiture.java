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
@Table(name = "TYPE_VOITURE")
public class TypeVoiture {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(name = "NOM")
	protected String nom;
	
	@Column(name = "TARIF_JOUR")
	protected Double tarifJour;
	
	@Column(name = "MONTANT_CAUTION")
	protected Double montantCaution;
	
	@OneToMany(mappedBy = "typeVoiture")
	private Set<Voiture> voitures = new HashSet<Voiture>(); 
	
	public TypeVoiture() {
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

	public Double getTarifJour() {
		return tarifJour;
	}

	public void setTarifJour(Double tarifJour) {
		this.tarifJour = tarifJour;
	}

	public Double getMontantCaution() {
		return montantCaution;
	}

	public void setMontantCaution(Double montantCaution) {
		this.montantCaution = montantCaution;
	}

	public Set<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(Set<Voiture> voitures) {
		this.voitures = voitures;
	}

}
