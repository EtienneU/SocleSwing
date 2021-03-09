package fr.diginamic.services.exemples;

import fr.diginamic.composants.MenuService;

public class Exemple1Service extends MenuService {

	@Override
	public void traitement() {
		console.clear();
		
		console.println("<h1 class='bg-dark-blue'><center>Exemple fond bleu foncé</center></h1>");
		console.println("<h1 class='bg-blue'><center>Exemple fond bleu</center></h1>");
		console.println("<h1 class='bg-green'><center>Exemple fond vert</center></h1>");
		console.println("<h1 class='bg-grey'><center>Exemple fond gris</center></h1>");
		console.println("<h1 class='bg-red'><center>Exemple fond rouge</center></h1>");
		console.println("<h1 class='bg-orange'><center>Exemple fond orange</center></h1>");
		console.println("<h1 class='bg-yellow'><center>Exemple fond jaune</center></h1>");
		console.println("<h1 class='bg-turquoise'><center>Exemple fond turquoise</center></h1>");

	}

}
