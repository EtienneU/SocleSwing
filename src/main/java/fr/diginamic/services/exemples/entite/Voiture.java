/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "VOITURE")
public class Voiture extends Vehicule {
	
	@Column(name = "NB_PLACES")
	private Byte nbPlaces;
	
	@ManyToOne
	@JoinColumn(name = "ID_TYPE_VOITURE")
	protected TypeVoiture typeVoiture;
	
	public Voiture() {
		super();
	}

	public Voiture(String immatriculation, Marque marque, Modele modele) {
		super(immatriculation, marque, modele);
	}

	public Byte getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Byte nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public TypeVoiture getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(TypeVoiture typeVoiture) {
		this.typeVoiture = typeVoiture;
	}

}
