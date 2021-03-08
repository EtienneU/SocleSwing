/**
 * 
 */
package fr.diginamic.services.exemples;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.services.exemples.dao.AbstractDao;
import fr.diginamic.services.exemples.dao.AgenceDao;
import fr.diginamic.services.exemples.dao.CamionDao;
import fr.diginamic.services.exemples.dao.ClientDao;
import fr.diginamic.services.exemples.dao.FactureDao;
import fr.diginamic.services.exemples.dao.MaintenanceDao;
import fr.diginamic.services.exemples.dao.MarqueDao;
import fr.diginamic.services.exemples.dao.ModeleDao;
import fr.diginamic.services.exemples.dao.PermisDao;
import fr.diginamic.services.exemples.dao.ReservationDao;
import fr.diginamic.services.exemples.dao.TypeCamionDao;
import fr.diginamic.services.exemples.dao.TypeVoitureDao;
import fr.diginamic.services.exemples.dao.VoitureDao;
import fr.diginamic.services.exemples.entite.Adresse;
import fr.diginamic.services.exemples.entite.Agence;
import fr.diginamic.services.exemples.entite.Camion;
import fr.diginamic.services.exemples.entite.Client;
import fr.diginamic.services.exemples.entite.Facture;
import fr.diginamic.services.exemples.entite.Maintenance;
import fr.diginamic.services.exemples.entite.Marque;
import fr.diginamic.services.exemples.entite.Modele;
import fr.diginamic.services.exemples.entite.PermisConduire;
import fr.diginamic.services.exemples.entite.Reservation;
import fr.diginamic.services.exemples.entite.Statut;
import fr.diginamic.services.exemples.entite.TypeCamion;
import fr.diginamic.services.exemples.entite.TypeVoiture;
import fr.diginamic.services.exemples.entite.Voiture;


/**
 * @author EtienneUrbano
 *
 */
public class TestConnexion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");		
		EntityManager em = AbstractDao.emf.createEntityManager();
		
		EntityTransaction transac = em.getTransaction();
		
		// ******* CREATION DES OBJETS AVANT INSERTION EN DB *******
		// Agence
		Agence 			vipLocation  = new Agence("VIP_Location", 34_580d, 0d);
		
		//Client
		Adresse 		adresse1 	 = new Adresse(14, "route du Moulin", 69005, "Lyon");
		Client 			client1 	 = new Client("Moustier", "Celine", "0945675432", "c.moustier@zig.com");
		PermisConduire 	permis1 	 = new PermisConduire("B", "Ed35288647", new Date(110, 5, 6));
		
		// Voiture
		Marque 			marque1 	 = new Marque("Alfa_Romeo");
		Modele 			modele1 	 = new Modele("Giulia", marque1);
		TypeVoiture 	sportive 	 = new TypeVoiture("Sportive", 123d, 565d);
		Voiture 		voiture1 	 = new Voiture(marque1, modele1, "FV-531-FO", Statut.DISPONIBLE, 1276, vipLocation, (byte)5, sportive);
		
		// Camion
		Marque 			marque2 	 = new Marque("Le_Petit_Forestier");
		Modele 			modele2 	 = new Modele("Glacière", marque2);
		TypeCamion 		frigorifique = new TypeCamion("Frigorifique", 123d, 565d);
		Camion 			camion1 	 = new Camion(marque2, modele2, "ZS-044-RN", Statut.EN_MAINTENANCE, 6978, vipLocation, (byte)12, frigorifique);		
		
		
		// Maintenance
		Maintenance 	maintenance1 = new Maintenance(camion1, new Date(121, 1, 4), new Date(121, 3, 16), 13700d);
		
		// Reservation
		Reservation   	resa1     	 = new Reservation(client1, voiture1, false, new Date(121, 3, 16), voiture1.getKilometrage(), new Date(121, 4, 26));
		
		// Facture
		Facture 		facture1	 = new Facture("54532ER", 3050d, false, client1, resa1);
		
		// ******* INSERTION EN DB VIA LES DAO *******
		transac.begin();
		
		AgenceDao agenceDao = new AgenceDao(em);
		vipLocation.getVehicules().add(voiture1);
		vipLocation.getVehicules().add(camion1);
		agenceDao.insert(vipLocation);
		
		PermisDao permisDao = new PermisDao(em);
		permisDao.insert(permis1);
		ClientDao clientDao = new ClientDao(em);
		client1.setAdresse(adresse1);
		client1.setPermisDeConduire(permis1);
		client1.getFactures().add(facture1);
		client1.getReservations().add(resa1);
		clientDao.insert(client1);
		
		MarqueDao marqueDao = new MarqueDao(em);
		marque1.getVehicules().add(voiture1);
		marque1.getModeles().add(modele1);
		marqueDao.insert(marque1);
		ModeleDao modeleDao = new ModeleDao(em);
		modele1.getVehicules().add(voiture1);
		modeleDao.insert(modele1);
		TypeVoitureDao typeVoitureDao = new TypeVoitureDao(em);
		sportive.getVoitures().add(voiture1);
		typeVoitureDao.insert(sportive);
		VoitureDao VoitureDao = new VoitureDao(em);
		voiture1.getReservations().add(resa1);
		VoitureDao.insert(voiture1);
		
		marqueDao.insert(marque2);
		modeleDao.insert(modele2);
		TypeCamionDao typeCamionDao = new TypeCamionDao(em);
		frigorifique.getCamions().add(camion1);
		typeCamionDao.insert(frigorifique);
		CamionDao camionDao = new CamionDao(em);
		camion1.getMaintenances().add(maintenance1);
		camionDao.insert(camion1);
		
		MaintenanceDao maintenanceDao = new MaintenanceDao(em);
		maintenanceDao.insert(maintenance1);
		
		ReservationDao reservDao = new ReservationDao(em);
		reservDao.insert(resa1);
		
		FactureDao factureDao = new FactureDao(em);
		factureDao.insert(facture1);

		
		transac.commit();
	}

}
