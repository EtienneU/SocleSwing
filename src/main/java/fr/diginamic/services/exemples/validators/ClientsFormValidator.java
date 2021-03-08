package fr.diginamic.services.exemples.validators;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.composants.validator.FormValidator;

/** validateur associé au formulaire
 * @author rbonn
 *
 */
public class ClientsFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String nom 			= form.getValue("champNom");
		String prenom 		= form.getValue("champPrenom");
		String numTel 		= form.getValue("champNumTel");
		String email 		= form.getValue("champEmail");
		String ville 		= form.getValue("champVille");
		String codePostal 	= form.getValue("champCodePostal");
		String rue 			= form.getValue("champRue");
		String numero 		= form.getValue("champNumero");
		
		if (nom.trim().isEmpty()) {
			console.alert("Le nom est obligatoire");
			return false;
		}
		else if (prenom.trim().isEmpty()) {
			console.alert("Le prénom est obligatoire");
			return false;
		}
		else if (numTel.trim().isEmpty()) {
			console.alert("Le numero de téléphone est obligatoire");
			return false;
		}
		else if (!valideNumTel(numTel)) {
			console.alert("Le numero de téléphone saisi est invalide");
			return false;
		}
		else if (email.trim().isEmpty()) {
			console.alert("L'e-mail est obligatoire");
			return false;
		}
		else if (ville.trim().isEmpty()) {
			console.alert("La ville est obligatoire");
			return false;
		}
		else if (codePostal.trim().isEmpty()) {
			console.alert("Le code postal est obligatoire");
			return false;
		}
		else if (rue.trim().isEmpty()) {
			console.alert("Le nom de la voie/rue est obligatoire");
			return false;
		}
		else if (numero.trim().isEmpty()) {
			console.alert("Le n° d'appartement/maison est obligatoire");
			return false;
		}
		return true;
	}
	
	public boolean valideNumTel(String num) {
		if (!num.matches("^[0-9]+") || num.length() != 10 
				|| num.charAt(0) != '0' || num.charAt(1) == '0') {
			return false;
		}
		return true;
	}

}
