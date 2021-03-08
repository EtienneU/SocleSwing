package fr.diginamic.services.exemples;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.db.SqlUtils;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
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
import fr.diginamic.services.exemples.entite.Personne;
import fr.diginamic.services.exemples.entite.Reservation;
import fr.diginamic.services.exemples.entite.Statut;
import fr.diginamic.services.exemples.entite.TypeCamion;
import fr.diginamic.services.exemples.entite.TypeVoiture;
import fr.diginamic.services.exemples.entite.Voiture;
import fr.diginamic.services.exemples.validators.ClientsFormValidator;
import fr.diginamic.services.exemples.validators.Exemple5FormValidator;

public class ServiceClients extends MenuService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");

	// Initialisation de la BD
	private void initDatabase() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transac = em.getTransaction();

		// ******* CREATION DES OBJETS AVANT INSERTION EN DB *******
		// Agence
		Agence vipLocation = new Agence("VIP_Location", 34_580d, 0d);

		// Client
		Adresse adresse1 = new Adresse(14, "route du Moulin", 69005, "Lyon");
		Client client1 = new Client("Moustier", "Celine", "0945675432", "c.moustier@zig.com");
		PermisConduire permis1 = new PermisConduire("B", "Ed35288647", new Date(110, 5, 6));

		// Voiture
		Marque marque1 = new Marque("Alfa_Romeo");
		Modele modele1 = new Modele("Giulia", marque1);
		TypeVoiture sportive = new TypeVoiture("Sportive", 123d, 565d);
		Voiture voiture1 = new Voiture(marque1, modele1, "FV-531-FO", Statut.DISPONIBLE, 1276, vipLocation, (byte) 5,
				sportive);

		// Camion
		Marque marque2 = new Marque("Le_Petit_Forestier");
		Modele modele2 = new Modele("Glacière", marque2);
		TypeCamion frigorifique = new TypeCamion("Frigorifique", 123d, 565d);
		Camion camion1 = new Camion(marque2, modele2, "ZS-044-RN", Statut.EN_MAINTENANCE, 6978, vipLocation, (byte) 12,
				frigorifique);

		// Maintenance
		Maintenance maintenance1 = new Maintenance(camion1, new Date(121, 1, 4), new Date(121, 3, 16), 13700d);

		// Reservation
		Reservation resa1 = new Reservation(client1, voiture1, false, new Date(121, 3, 16), voiture1.getKilometrage(),
				new Date(121, 4, 26));

		// Facture
		Facture facture1 = new Facture("54532ER", 3050d, false, client1, resa1);

		// ******* INSERTION EN DB VIA LES DAO *******
		transac.begin();

		AgenceDao agenceDao = new AgenceDao(em);
		vipLocation.getVehicules().add(voiture1);
		vipLocation.getVehicules().add(camion1);
		agenceDao.insert(vipLocation);

		PermisDao permisDao = new PermisDao(em);
		permisDao.insert(permis1);
		ClientDao clientDao = new ClientDao(em);
		client1.setPermisDeConduire(permis1);
		client1.setAdresse(adresse1);
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

//	private void initDatabase() {
//		EntityManager em = emf.createEntityManager();
//		TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p", Personne.class);
//		List<Personne> clients = query.getResultList();
//		if (clients.size() == 0) {
//			SqlUtils.executeFile("exemple.sql", em);
//		}
//	}

	/**
	 * Méthode appelée lors du clic sur le menu "Lister et gérer les clients"
	 * Elle réalise l'extraction et l'affichage des clients de la DB
	 * 
	 * @param aucun
	 */
	@Override
	public void traitement() {

		EntityManager em = emf.createEntityManager();

		// initDatabase() permet d'initialiser la base avec des données de tests
		initDatabase();

		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		List<Client> clients = query.getResultList();

		console.clear();
		console.println("<h1 class='bg-green'><center>Liste des clients</center></h1>");

		// le terme html "href='creer()'" fait directement appel à ma méthode du même nom, lors du clic
		String html = "<button><a class='btn-blue' href='creer()'>CREER UN NOUVEAU CLIENT</a></button></br>"
				+ "<table class='table' cellspacing=0>"
				+ "<tr class='bg-green'>"
				+ "		<td>&nbsp;</td>"
				+ "		<td>&nbsp;</td>"
				+ "		<td>Nom</td>"
				+ "		<td>Prénom</td>"
				+ "		<td>E-mail</td>"
				+ "		<td>N° tel.</td>"
				+ "		<td>Ville</td>"
				+ "</tr>";
		for (Client c : clients) {
			html += "<tr>" 
					+ "	<td><a class='btn-blue' href='modifier(" + c.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ " <td><a class='btn-red' href='supprimer(" + c.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					+ " <td width='110px'>" + c.getNom().toUpperCase() + "</td>" 
					+ " <td width='110px'>" + c.getPrenom() + 	"</td>"
					+ " <td width='150px'>" + c.getEmail()  + 	"</td>"
					+ " <td width='110px'>" + c.getNumTel() + 	"</td>"
					+ " <td width='150px'>" + c.getAdresse().getVille() + "</td>"
					+ "</tr>";
		}
		html += "</table>";

		console.print(html);
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur une icone de modification
	 * dans la table des clients.
	 * 
	 * @param id identifiant du client à modifier.
	 */
	public void creer() {

		System.out.println("Clic sur bouton \"CREER UN NOUVEAU CLIENT\"");
		EntityManager em = emf.createEntityManager();

		// Création du formulaire
		Form form = new Form();
		form.addInput(new TextField("Nom: ", "champNom"));
		form.addInput(new TextField("Prénom: ", "champPrenom"));
		form.addInput(new TextField("Numéro de tel: ", "champNumTel"));
		form.addInput(new TextField("E-mail: ", "champEmail"));
		form.addInput(new TextField("Ville: ", "champVille"));
		form.addInput(new TextField("Code postal: ", "champCodePostal"));
		form.addInput(new TextField("Rue ou voie: ", "champRue"));
		form.addInput(new TextField("Numero: ", "champNumero"));

		// Vérification des règles métier dans le validator
		ClientsFormValidator validator = new ClientsFormValidator();

		boolean valide = console.input("Ajout d'un client ", form, validator);
		if (valide) {

			Adresse adresse = new Adresse(Integer.parseInt(form.getValue("champNumero")), form.getValue("champRue"),
					Integer.parseInt(form.getValue("champCodePostal")), form.getValue("champVille"));
			Client client = new Client(form.getValue("champNom"), form.getValue("champPrenom"),
					adresse, form.getValue("champNumTel"), form.getValue("champEmail"));
			
			ClientDao clientDao = new ClientDao(em);
			
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			clientDao.insert(client);
			transaction.commit();

			traitement();
		}
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur une icone de modification
	 * dans la table des clients.
	 * 
	 * @param id identifiant du client à modifier.
	 */
	public void modifier(Integer id) {

		EntityManager em = emf.createEntityManager();
		Client c = em.find(Client.class, id);
		ClientDao clientDao = new ClientDao(em);

		// On commence par créér le formulaire vide
		Form form = new Form();

		// On ajoute au formulaire 2 champs de type texte pour permettre de modifier le
		// nom et le prénom du client
		form.addInput(new TextField("Nom:", "champNom", c.getNom()));
		form.addInput(new TextField("Prénom:", "champPrenom", c.getPrenom()));
		form.addInput(new TextField("E-mail:", "champEmail", c.getEmail()));
		form.addInput(new TextField("N° tel:", "champNumTel", c.getNumTel()));
		form.addInput(new TextField("Ville:" , "champVille", c.getAdresse().getVille()));
		form.addInput(new TextField("Code postal:", "champCodePostal", c.getAdresse().getCodePostal().toString()));
		form.addInput(new TextField("Rue:", "champRue", c.getAdresse().getRue()));
		form.addInput(new TextField("Numero:", "champNumero", c.getAdresse().getNumero().toString()));

		// Les règles métier sont vérifiées dans le validator, passé en paramètre de console.input()
		ClientsFormValidator validator = new ClientsFormValidator();

		boolean valide = console.input("Modification du client " + c.getPrenom() + " " + c.getNom().toUpperCase(), form, validator);
		if (valide) {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			String nvNom    = form.getValue("champNom");
			String nvPrenom = form.getValue("champPrenom");
			String nvEmail  = form.getValue("champEmail");
			String nvNumTel = form.getValue("champNumTel");
			String nvVille  = form.getValue("champVille");
			String nvCP     = form.getValue("champCodePostal");
			String nvRue    = form.getValue("champRue");
			String nvNumero = form.getValue("champNumero");
			
			c.setNom(nvNom);
			c.setPrenom(nvPrenom);
			c.getAdresse().setNumero(Integer.parseInt(nvNumero));
			c.getAdresse().setCodePostal(Integer.parseInt(nvCP));
			c.getAdresse().setRue(nvRue);
			c.getAdresse().setVille(nvVille);
			transaction.commit();

			traitement();
		}
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur une icone de suppression
	 * dans la table des clients
	 * 
	 * @param id identifiant du client à supprimer.
	 */
	public void supprimer(Integer id) {
		boolean result = console.confirm("Suppression de l'item " + id,
				"Confirmez-vous la suppression de l'item n°" + id);
		
		System.out.println("Clic sur bouton \"suppression du client\"");
		EntityManager em = emf.createEntityManager();
		ClientDao clientDao = new ClientDao(em);

		
		if (result) {
			Client c = em.find(Client.class, id);
			EntityTransaction transaction = em.getTransaction();
			
			transaction.begin();
			clientDao.delete(c);
			transaction.commit();

			traitement();
			console.alert("Suppression du client suivant : " + c.getPrenom() + " " + c.getNom().toUpperCase());
		}
		traitement();
	}

}
