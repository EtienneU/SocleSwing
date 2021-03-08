package fr.diginamic.services.exemples.validators;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

/** validateur associé au formulaire
 * @author rbonn
 *
 */
public class Exemple5FormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String nvNom = form.getValue("champNom");
		String nvPrenom = form.getValue("champPrenom");
		
		if (nvNom.trim().isEmpty()) {
			console.alert("Le nom est obligatoire !");
			return false;
		}
		else if (nvPrenom.trim().isEmpty()) {
			console.alert("Le prénom est obligatoire !");
			return false;
		}
		return true;
	}

}
