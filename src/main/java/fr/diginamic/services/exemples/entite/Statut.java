/**
 * 
 */
package fr.diginamic.services.exemples.entite;


/**
 * @author EtienneUrbano
 *
 */
public enum Statut {

	DISPONIBLE		("Disponible"),
	LOUE			("Liquide"),
	EN_MAINTENANCE	("En_Maintenance");
	
	private String libelle;
	
	private Statut(String libelle) {
		this.libelle = libelle;
	}
	
	public static Statut find(String libelle) {
		Statut[] status = Statut.values();
		for (Statut s : status) {
			if (s.getLibelle().equals(libelle)) {
				return s;
			}
		}
		// Le retour par defaut
		return EN_MAINTENANCE;
	}

	public String getLibelle() {
		return libelle;
	}
	

}
