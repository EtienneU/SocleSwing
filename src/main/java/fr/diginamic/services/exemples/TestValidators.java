/**
 * 
 */
package fr.diginamic.services.exemples;

import java.util.Scanner;

import fr.diginamic.services.exemples.validators.ClientsFormValidator;

/**
 * @author EtienneUrbano
 *
 */
public class TestValidators {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ClientsFormValidator v = new ClientsFormValidator();
		String num = "xxx";
		
		while (!v.valideNumTel(num)) {
			System.out.print(num + " est invalide... Saisir un n° de tel: ");
			num = scan.nextLine();
		} 
		
		System.out.println(num + " EST VALIDE !");
	}

}
