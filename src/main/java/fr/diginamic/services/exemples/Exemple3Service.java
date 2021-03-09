package fr.diginamic.services.exemples;

import fr.diginamic.composants.MenuService;

public class Exemple3Service extends MenuService {

	@Override
	public void traitement() {
		console.clear();
		
		console.println("<h1 class='bg-dark-blue'><center>Exemple fond bleu foncé</center></h1>");
		
		// Utilisation de la classe bg-green pour l'entêtete de la table
		String html = "<table cellspacing=0>"
				    + "   <tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Date de naissance</td></tr>"
		            + "   <tr><td>MBAPPE</td><td>Kylian</td><td>21/10/1997</td></tr>"
			        + "   <tr><td>NEYMAR</td><td>Junior</td><td>05/03/1993</td></tr>"
			        + "   <tr><td>MOISE</td><td>Kean</td><td>08/07/1995</td></tr>"
		            + "</table>";
		console.print(html);
	}
}
