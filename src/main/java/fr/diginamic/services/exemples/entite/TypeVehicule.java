/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.Arrays;
import java.util.List;


/**
 * @author EtienneUrbano
 *
 */
public enum TypeVehicule {
	
	CAMION	("Camion"),
	VOITURE	("Voiture");
	
	private String libelle;
	
	private TypeVehicule(String libelle) {
		this.libelle = libelle;
	}
	
	public static List<TypeVehicule> findAll(String libelle) {
		return Arrays.asList(TypeVehicule.values());
	}

	public String getLibelle() {
		return libelle;
	}
	

}
