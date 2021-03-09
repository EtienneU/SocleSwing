package fr.diginamic.services.exemples;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.dao.AgenceDao;
import fr.diginamic.services.exemples.dao.CamionDao;
import fr.diginamic.services.exemples.dao.ClientDao;
import fr.diginamic.services.exemples.dao.MarqueDao;
import fr.diginamic.services.exemples.dao.ModeleDao;
import fr.diginamic.services.exemples.dao.TypeCamionDao;
import fr.diginamic.services.exemples.dao.TypeVoitureDao;
import fr.diginamic.services.exemples.dao.VehiculeDao;
import fr.diginamic.services.exemples.entite.Adresse;
import fr.diginamic.services.exemples.entite.Agence;
import fr.diginamic.services.exemples.entite.Camion;
import fr.diginamic.services.exemples.entite.Client;
import fr.diginamic.services.exemples.entite.Marque;
import fr.diginamic.services.exemples.entite.Modele;
import fr.diginamic.services.exemples.entite.Statut;
import fr.diginamic.services.exemples.entite.TypeCamion;
import fr.diginamic.services.exemples.entite.TypeVehicule;
import fr.diginamic.services.exemples.entite.TypeVoiture;
import fr.diginamic.services.exemples.entite.Vehicule;
import fr.diginamic.services.exemples.entite.Voiture;
import fr.diginamic.services.exemples.validators.ClientsFormValidator;
import fr.diginamic.services.exemples.validators.VehiculesFormValidator;

public class ServiceVehicules extends MenuService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");

	// Initialisation de la BD
	private void initDB() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Vehicule> query = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);
		List<Vehicule> vehicules = query.getResultList();
		if (vehicules.size() == 0) {
			ServiceClients serviceClients = new ServiceClients();
			serviceClients.initDB();
		}
	}
	

	/**
	 * Méthode appelée lors du clic sur le menu "Lister et gérer les véhicules"
	 * Ou appelée en fin de méthode CRUD
	 * Elle réalise l'extraction et l'affichage des véhicules de la DB
	 * 
	 * @param aucun
	 */
	@Override
	public void traitement() {

		EntityManager em = emf.createEntityManager();

		// Initialisation de la DB si elle est vide
		initDB();

		TypedQuery<Vehicule> query = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);
		List<Vehicule> vehicules = query.getResultList();

		console.clear();
		console.println("<h1 class='bg-red'><center>Liste des vehicules</center></h1>");

		// le terme html "href='ajouter()'" fait directement appel à ma méthode du même nom, lors du clic
		String html = "<button><a class='btn-blue' href='ajouterCamion()'>Ajouter un nouveau CAMION</a></button></br>"
				+ "<button><a class='btn-blue' href='ajouterVoiture()'>Ajouter une nouvelle VOITURE</a></button></br>"
				+ "<table class='table' cellspacing=0>"
				+ "<tr class='bg-red'>"
				+ "		<td>&nbsp;</td>"
				+ "		<td>&nbsp;</td>"
				+ "		<td>Type</td>"
				+ "		<td>Sous-type</td>"
				+ "		<td>Volume (m3)</td>"
				+ "		<td>Nbre places</td>"
				+ "		<td>Marque</td>"
				+ "		<td>Modèle</td>"
				+ "		<td>Immatriculation</td>"
				+ "		<td>Kilometrage</td>"
				+ "		<td>Statut</td>"
				+ "</tr>";
		for (Vehicule v : vehicules) {
			html += "<tr>" 
					+ "	<td><a class='btn-blue' href='modifier(" + v.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ " <td><a class='btn-red' href='supprimer(" + v.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					+ " <td width='80px'>" + v.getTypeVehicule().toString() + "</td>";
			if (v.getTypeVehicule() == TypeVehicule.CAMION) {
				Camion camion = em.find(Camion.class, v.getId()); 
				html += " <td width='80px'>" + camion.getTypeCamion().getNom() + "</td>"
					   + "<td width='80px'>" + camion.getVolume() + "</td>"
				       + "<td width='80px'> / </td>";
			} else {
				Voiture voiture = em.find(Voiture.class, v.getId()); 
				html += " <td width='80px'>" + voiture.getTypeVoiture().getNom() + "</td>"
					   + "<td width='80px'> / </td>"
					   + "<td width='80px'>" + voiture.getNbPlaces() + "</td>";
			}
			html +=   " <td width='135px'>" + v.getMarque().getNom().replaceAll("_", " ").toUpperCase() + "</td>"
					+ " <td width='110px'>" + v.getModele().getNom() + "</td>"
					+ " <td width='95px'>" + v.getImmatriculation() + "</td>"
					+ " <td width='85px'>" + v.getKilometrage() + "</td>"
					+ " <td width='130px'>" + v.getStatut().getLibelle().replaceAll("_", " ") + "</td>"
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
	public void ajouterCamion() {

		System.out.println("Clic sur bouton \"Ajouter un nouveauCAMION\"");
		EntityManager em = emf.createEntityManager();

		// Création du formulaire
		Form form = new Form();
		form.addInput(new TextField("Type de camion: ", "champTypeCamion"));
		form.addInput(new TextField("Marque: ", "champMarque"));
		form.addInput(new TextField("Modèle: ", "champModele"));
		form.addInput(new TextField("Immatriculation: ", "champImmatri"));
		form.addInput(new TextField("Kilométrage: ", "champKm"));
		form.addInput(new TextField("Statut: ", "champStatut"));
		form.addInput(new TextField("Volume: ", "champVolume"));
		form.addInput(new TextField("Agence: ", "champAgence"));

		// Vérification des règles métier dans le validator
		VehiculesFormValidator validator = new VehiculesFormValidator();

		boolean valide = console.input("Ajout d'un camion ", form, validator);
		if (valide) {
			
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			CamionDao 		camionDao 		= new CamionDao(em);
			AgenceDao 		agenceDao 		= new AgenceDao(em);
			MarqueDao 		marqueDao 		= new MarqueDao(em);
			ModeleDao 		modeleDao 		= new ModeleDao(em);
			TypeCamionDao 	typeCamionDao 	= new TypeCamionDao(em);
			
			Marque marque = marqueDao.findByNom(form.getValue("champMarque"));
			if (marque == null) {
				marque = new Marque(form.getValue("champMarque"));
				marqueDao.insert(marque);
			}
			Modele modele = modeleDao.findByNom(form.getValue("champModele"));
			if (modele == null) {
				modele = new Modele(form.getValue("champModele"), marque);
				modeleDao.insert(modele);
				marque.getModeles().add(modele);
			}
			String immatriculation = form.getValue("champImmatri").toString();
			Statut statut = Statut.find(form.getValue("champStatut"));
			Integer km = Integer.parseInt(form.getValue("champKm"));
			Agence agence = agenceDao.findByNom(form.getValue("champAgence"));
			if (agence == null) {
				agence = new Agence(form.getValue("champAgence"));
				agenceDao.insert(agence);
			}
			Byte volume = Byte.parseByte(form.getValue("champVolume"));
			TypeCamion typeCamion = typeCamionDao.findByNom(form.getValue("champTypeCamion").toString().toLowerCase());
			if (typeCamion == null) {
				typeCamion = new TypeCamion(form.getValue("champTypeCamion"), 100d, 1500d);
				typeCamionDao.insert(typeCamion);
			}
			
			Camion camion = new Camion(marque, modele, immatriculation, Statut.DISPONIBLE, km, agence, volume, typeCamion);
			camionDao.insert(camion);
			
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
		CamionDao 		camionDao 		= new CamionDao(em);
		AgenceDao 		agenceDao 		= new AgenceDao(em);
		MarqueDao 		marqueDao 		= new MarqueDao(em);
		ModeleDao 		modeleDao 		= new ModeleDao(em);
		TypeCamionDao 	typeCamionDao 	= new TypeCamionDao(em);
		TypeVoitureDao  typeVoitureDao  = new TypeVoitureDao(em);
		
		Vehicule v = em.find(Vehicule.class, id);		

		// On commence par créér le formulaire vide
		Form form = new Form();

		// Ajout au formulaire des champs de type texte pour modifier le véhicule
		form.addInput(new TextField("Marque: ", "champMarque", v.getMarque().getNom()));
		form.addInput(new TextField("Modèle: ", "champModele", v.getModele().getNom()));
		form.addInput(new TextField("Immatriculation: ", "champImmatri", v.getImmatriculation()));
		form.addInput(new TextField("Kilométrage: ", "champKm", v.getKilometrage().toString()));
		form.addInput(new TextField("Statut: ", "champStatut", v.getStatut().getLibelle()));
		form.addInput(new TextField("Agence: ", "champAgence", v.getAgence().getNom()));
		if (v.getTypeVehicule() == TypeVehicule.CAMION) {
			Camion camion = em.find(Camion.class, id); 
			form.addInput(new TextField("Volume (m3): ", "champVolume", String.valueOf(camion.getVolume())));
			form.addInput(new TextField("Type de camion: ", "champTypeCamion", camion.getTypeCamion().getNom()));
		} else {
			Voiture voiture = em.find(Voiture.class, id); 
			form.addInput(new TextField("Nombre de places: ", "champNbPlaces", String.valueOf(voiture.getNbPlaces())));
			form.addInput(new TextField("Type de voiture: ", "champTypeVoiture", voiture.getTypeVoiture().getNom()));
		}
		
		// Les règles métier sont vérifiées dans le validator, passé en paramètre de console.input()
		VehiculesFormValidator validator = new VehiculesFormValidator();

		boolean valide = console.input("Modification du vehicule " + v.getMarque().getNom() + " " + v.getModele().getNom(), form, validator);
		if (valide) {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			if (v.getTypeVehicule() == TypeVehicule.CAMION) {
				TypeCamion nvTypeCamion = typeCamionDao.findByNom(form.getValue("champTypeCamion").toString().trim().toLowerCase());
				if (nvTypeCamion == null) {
					nvTypeCamion = new TypeCamion(form.getValue("champTypeCamion").toString().trim(), 100d, 1500d);
					typeCamionDao.insert(nvTypeCamion);
				}
				Byte nvVolume = form.getValue("champVolume");
				Camion camion = em.find(Camion.class, v.getId());
				camion.setVolume(nvVolume);
				camion.setTypeCamion(nvTypeCamion);
			} else {
				TypeVoiture nvTypeVoiture = typeVoitureDao.findByNom(form.getValue("champTypeVoiture").toString().trim().toLowerCase());
				if (nvTypeVoiture == null) {
					nvTypeVoiture = new TypeVoiture(form.getValue("champTypeVoiture").toString().trim(), 100d, 1500d);
					typeVoitureDao.insert(nvTypeVoiture);
				}
				String nvNbPlaces = form.getValue("champNbPlaces");
			}
			
			Marque nvMarque = marqueDao.findByNom(form.getValue("champMarque"));
			if (nvMarque == null) {
				nvMarque = new Marque(form.getValue("champMarque"));
				marqueDao.insert(nvMarque);
			}
			v.setMarque(nvMarque);
			
			Modele nvModele = modeleDao.findByNom(form.getValue("champModele"));
			if (nvModele == null) {
				nvModele = new Modele(form.getValue("champModele"), nvMarque);
				modeleDao.insert(nvModele);
				nvMarque.getModeles().add(nvModele);
			}
			v.setModele(nvModele);
			
			String nvImmatri = form.getValue("champImmatri");
			v.setImmatriculation(nvImmatri);

			Statut nvStatut = Statut.find(form.getValue("champStatut"));
			v.setStatut(nvStatut);

			String nvKilometrage = form.getValue("champKm");
			v.setKilometrage(Integer.parseInt(nvKilometrage));

			Agence nvAgence = agenceDao.findByNom(form.getValue("champAgence"));
			if (nvAgence == null) {
				nvAgence = new Agence(form.getValue("champAgence"));
				agenceDao.insert(nvAgence);
			}

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
		EntityManager em = emf.createEntityManager();
		ClientDao clientDao = new ClientDao(em);
		
		Client clientDB = clientDao.findById(id);
		String identite = clientDB.getPrenom() + " " + clientDB.getNom().toUpperCase();
		
		boolean result = console.confirm("Suppression du client " + identite,
				"Confirmez-vous la suppression de " + identite + " ?");
		
		System.out.println("Clic sur bouton \"suppression du client\"");

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
