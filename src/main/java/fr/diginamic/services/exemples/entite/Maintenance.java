/**
 * 
 */
package fr.diginamic.services.exemples.entite;

import java.util.Date;
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
public class Maintenance {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE")
	private Vehicule vehicule;
	
	@Column(name = "DATE_DEBUT")
	private Date dateDebut;
	
	@Column(name = "DATE_FIN")
	private Date dateFin;
	
	@Column(name = "COUT")
	private Double cout;
	
	public Maintenance() {
		
	}

	public Maintenance(Vehicule vehicule, Date dateDebut, Date dateFin, Double cout) {
		this.vehicule = vehicule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.cout = cout;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getCout() {
		return cout;
	}

	public void setCout(Double cout) {
		this.cout = cout;
	}

}

