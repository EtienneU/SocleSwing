/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name="PERMIS_DE_CONDUIRE")
public class PermisConduire {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "NUMERO")
	private String numero;
	
	@Column(name = "DATE_OBTENTION")
	private Date dateObtention;
	
	@OneToOne
	@JoinColumn(name = "ID_CLIENT")
	private Client client;
	
	public PermisConduire() {
		
	}
	
	public PermisConduire(String type, String numero, Date dateObtention, Client client) {
		this.type = type;
		this.numero = numero;
		this.dateObtention = dateObtention;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateObtention() {
		return dateObtention;
	}

	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
