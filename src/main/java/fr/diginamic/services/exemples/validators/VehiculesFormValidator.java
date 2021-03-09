package fr.diginamic.services.exemples.validators;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.composants.validator.FormValidator;

/** validateur associé au formulaire
 * @author rbonn
 *
 */
public class VehiculesFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String typeCamion 		= form.getValue("champTypeCamion");
		String marque 		 	= form.getValue("champMarque");
		String modele 		  	= form.getValue("champModele");
		String immatriculation 	= form.getValue("champImmatri");
		String kilometrage 		= form.getValue("champKm");
		String statut 			= form.getValue("champStatut");
		String volume 			= form.getValue("champVolume");
		
		if (typeCamion.trim().isEmpty()) {
			console.alert("Le type de camion est obligatoire");
			return false;
		}
		else if (marque.trim().isEmpty()) {
			console.alert("La marque est obligatoire");
			return false;
		}
		else if (marque.trim().length() > 30) {
			console.alert("La marque ne doit pas excéder 30 caractères");
			return false;
		}
		else if (modele.trim().isEmpty()) {
			console.alert("Le modele est obligatoire");
			return false;
		}
		else if (modele.trim().length() > 30) {
			console.alert("Le modèle ne doit pas excéder 30 caractères");
			return false;
		}
		else if (immatriculation.trim().isEmpty()) {
			console.alert("Le n° d'immatriculation est obligatoire");
			return false;
		}
		else if (!valideImmatriculation(immatriculation)) {
			console.alert("Le n° d'immatriculation doit être au format 'AB-123-CD'");
			return false;
		}
		else if (kilometrage.trim().isEmpty()) {
			console.alert("Le kilometrage est obligatoire");
			return false;
		}
		else if (statut.trim().isEmpty()) {
			console.alert("Le statut est obligatoire");
			return false;
		}
		else if (volume.trim().isEmpty()) {
			console.alert("La capacité volumique est obligatoire");
			return false;
		}
		else if (!volume.trim().matches("\\d+") || Byte.parseByte(volume.trim()) > 30) {
			console.alert("Le volume saisi est invalide ou dépasse 30 m3");
			return false;
		}
		return true;
	}
	
	public boolean valideImmatriculation(String immatri) {
		if (!immatri.matches("^[A-Za-z]{1,2}-[0-9]{1,3}-[A-Za-z]{1,2}$") 
				|| immatri.length() < 7 || immatri.length() > 10) {
			return false;
		}
		return true;
	}

}
