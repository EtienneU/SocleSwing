package fr.diginamic.services.exemples;

import fr.diginamic.composants.MenuService;

public class Exemple4Service extends MenuService {

	@Override
	public void traitement() {
		console.clear();
		
		console.println("<h1 class='bg-dark-blue'><center>Exemple fond bleu foncé</center></h1>");
		
		// Utilisation de la classe bg-green pour l'entêtete de la table
		String html = "<table cellspacing=0>"
				    + "   <tr class='bg-green'><td>&nbsp;</td><td>Nom</td><td>Prénom</td></tr>"
		            + "   <tr><td><a href='modifier(1)'>Modifier</a></td><td>MBAPPE</td><td>Kylian</td></tr>"
			        + "   <tr><td><a href='modifier(2)'>Modifier</a></td><td>NEYMAR</td><td>Junior</td></tr>"
			        + "   <tr><td><a href='toto(3)'>Modifier</a></td><td>MOISE</td><td>Kean</td></tr>"
		            + "</table>";
		console.print(html);
	}

	/** Méthode appelée lorsque l'utilisateur clique sur le bouton Modifier dans la table
	 * @param id
	 */
	protected void modifier(Long id) {
		console.println("Vous venez d'invoquer la méthode modifier avec l'identiant "+id);
	}
	
	/** Méthode appelée lorsque l'utilisateur clique sur le bouton Modifier dans la table
	 * @param id
	 */
	protected void toto(Long id) {
		console.println("Vous venez d'invoquer la méthode toto avec l'identiant "+id);
	}

}
