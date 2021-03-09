/**
 * 
 */
package fr.diginamic.services.exemples;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.services.exemples.dao.AgenceDao;
import fr.diginamic.services.exemples.entite.Agence;
import fr.diginamic.services.exemples.validators.ClientsFormValidator;
import fr.diginamic.services.exemples.validators.VehiculesFormValidator;

/**
 * @author EtienneUrbano
 *
 */
public class TestValidators {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TEST DU NUMERO DE TEL
//		Scanner scan = new Scanner(System.in);
//		ClientsFormValidator v = new ClientsFormValidator();
//		String num = "xxx";
//		
//		while (!v.valideNumTel(num)) {
//			System.out.print(num + " est invalide... Saisir un n° de tel: ");
//			num = scan.nextLine();
//		} 
//		
//		System.out.println(num + " EST VALIDE !");
		
		
		// TEST DU NUMERO D'IMMATRICULATION
//		Scanner scan = new Scanner(System.in);
//		VehiculesFormValidator v = new VehiculesFormValidator();
//		String immatri = "xxx";
//		
//		while (!v.valideImmatriculation(immatri)) {
//			System.out.print("\"" + immatri + "\" invalide... Saisir un n° d'immatriculation : ");
//			immatri = scan.nextLine(); 
//		} 
//		System.out.print("\"" + immatri + "\" VALIDE =) ");
		
		// TEST DE LA METHODE DE RECHERCHE de AgenceDao
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-location-voiture");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transac = em.getTransaction();
		AgenceDao agenceDao = new AgenceDao(em);
		for (Agence a : agenceDao.findAll()) {
			System.out.println(a);
		}
		System.out.println();

	}

}
