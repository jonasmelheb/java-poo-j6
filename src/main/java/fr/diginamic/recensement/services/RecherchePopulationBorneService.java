package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.ReflectionException;
import fr.diginamic.recensement.services.check.DepartementCheck;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws ReflectionException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		if (!DepartementCheck.departementcheck(Integer.parseInt(choix))) {
			throw new ReflectionException("Département invalide");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();;
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new ReflectionException("Vous entrez un nombre non valide");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();;
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new ReflectionException("Vous entrez un nombre non valide");
		}

		if (Integer.parseInt(saisieMin) > Integer.parseInt(saisieMax)) {
			throw new ReflectionException("Vous entrez un nombre min supériere à un nombre max");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

}
