/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author EtienneUrbano
 *
 */
@Embeddable
public class Adresse {

	@Column(name="NUMERO_RUE")
	private Integer numeroRue;
	
	@Column(name="LIBELLE_RUE")
	private String rue;
	
	@Column(name="CODE_POSTAL")
	private Integer codePostal;
	
	@Column(name="VILLE")
	private String ville;
	
	public Adresse() {
	}

	public Integer getNumero() {
		return numeroRue;
	}

	public void setNumero(Integer numero) {
		this.numeroRue = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
