package fr.diginamic.services.exemples;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.services.exemples.entite.Vehicule;

public class Exemple6Service extends MenuService {

	@Override
	public void traitement() {

		console.clear();
		console.print("<h1 class='bg-turquoise'><center>Titre avec fond bleu clair</center></h1>");

		// On commence par créér le formulaire vide
		Form form = new Form();
		
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Nom:", "champ1"));
		form.addInput(new TextField("Prénom:", "champ2"));
		
		// Champ de type date
		form.addInput(new DateField("Date de naissance :", "dateNaissance"));
		
		
		List<Selectable> vehicules = new ArrayList<>();
//		vehicules.add(new Vehicule(1L, "AA-552-CD", "Peugeot", "208"));
//		vehicules.add(new Vehicule(2L, "AW-227-XE", "Peugeot", "3008"));
//		vehicules.add(new Vehicule(3L, "XX-131-XT", "Peugeot", "3008"));
		
		// Champ de type liste de sélection
		form.addInput(new ComboBox("Liste de véhicules:", "vehicule", vehicules, vehicules.get(2)));

		// Création d'un validator qui stocke les règles de gestion
		Exemple6FormValidator validator = new Exemple6FormValidator();
		
		// La méthode suivante permet d’afficher le formulaire.
		// La méthode retourne false si l’utilisateur a cliqué sur Annuler, sinon 
		// retourne true
		boolean valide = console.input("Demande d'informations", form, validator);

		// Récupéation des informations saisies
		if (valide) {
			console.print("Vous vous <b>appelez</b> ").println("<span style='color:red'>"+form.getValue("champ2")+" "+form.getValue("champ1")+"</span>");
			console.println("Date de naissance :"+form.getValue("dateNaissance"));
			console.println("Voiture sélectionnée :"+form.getValue("vehicule"));
		}
	}

	public void modifier(Long id) {
		console.println("Modification de l'item " + id, Color.GREEN);
	}

	public void supprimer(Long id) {
		console.println("Suppression de l'item " + id, Color.RED);
	}

}
