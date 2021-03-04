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
@Table(name = "CAMION")
public class Camion extends Vehicule {
	
	@Column(name = "VOLUME")
	private Byte volume;
	
	@ManyToOne
	@JoinColumn(name = "ID_TYPE_CAMION")
	protected TypeCamion typeCamion;
	
	public Camion() {
		super();
	}

	public Camion(String immatriculation, Marque marque, Modele modele) {
		super(immatriculation, marque, modele);
	}

	public byte getVolume() {
		return volume;
	}

	public void setVolume(byte volume) {
		this.volume = volume;
	}

	public TypeCamion getTypeCamion() {
		return typeCamion;
	}

	public void setTypeCamion(TypeCamion typeCamion) {
		this.typeCamion = typeCamion;
	}

	public void setVolume(Byte volume) {
		this.volume = volume;
	}

}
