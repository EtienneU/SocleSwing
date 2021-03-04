/**
 * 
 */
package fr.diginamic.services.exemples.entite;


/**
 * @author EtienneUrbano
 *
 */
public enum Reglement {

	CB			("CB"),
	LIQUIDE		("Liquide"),
	CHEQUE		("Cheque");
	
	private String libelle;
	
	private Reglement(String libelle) {
		this.libelle = libelle;
	}
	
	public static Reglement find(String libelle) {
		Reglement[] reglements = Reglement.values();
		for (Reglement r : reglements) {
			if (r.getLibelle().equals(libelle)) {
				return r;
			}
		}
		return null;
	}

	public String getLibelle() {
		return libelle;
	}

}
