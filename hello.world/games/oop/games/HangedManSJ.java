package oop.games;

import java.io.InputStream;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class HangedManSJ {

	public static String[] words; // notre dictionnaire
	private static String the_mot; // le mot choisi
	private static char[] mot_a_trouver; // le mot a deviner
	private static int nb_vies; // nombre de vies

	/*
	 * Le point d'entrée, réduit au minimum comme cela devrait être la plupart du
	 * temps. La liste des mots possibles à deviner est donnée comme argument.
	 */
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		init(args, System.in, System.out);
		play(System.in, System.out, scanner);

	}

	/*
	 * Initialisation des variables globales (champs statiques).
	 */
	public static void init(String[] args, InputStream is, PrintStream ps) {
		words = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			words[i] = args[i];
		}
		ps.println("Bienvenue sur le jeu du PENDU !\n");
		nb_vies = 10;
	}

	/*
	 * Une fonction qui joue une partie et propose aux joueurs de continuer ou non.
	 * La fonction choisit le mot à deviner parmi la liste des mots possibles, en
	 * utilisant un nombre généré aléatoirement. Voir la classe Random dans le
	 * package java.util.
	 */
	public static void play(InputStream is, PrintStream ps, Scanner scanner) {
		String reponse = "oui";

		Random random = new Random();

		while (reponse.equals("oui")) {

			int index = random.nextInt(words.length);
			the_mot = words[index];

			initWord(index);
			playGame(is, ps, scanner);

			ps.print("\nVoulez-vous jouer à nouveau ? (oui/non): ");
			reponse = scanner.nextLine();
		}

		ps.print("Merci d'avoir joué, à bientôt !");
		scanner.close();
	}

	/*
	 * La fonction initialise le jeu pour le mot choisi, donné par son index dans le
	 * tableau des mots.
	 */
	public static void initWord(int n) {
		the_mot = words[n];
		nb_vies = 10;
		mot_a_trouver = new char[the_mot.length()];
		for (int i = 0; i < mot_a_trouver.length; i++) {
			mot_a_trouver[i] = '_';
		}
	}

	/*
	 * Une fonction pour savoir si le mot actuel a été deviné ou non. Renvoie true
	 * si le mot actuel a été deviné.
	 */
	public static boolean guessed() {
		for (char c : mot_a_trouver) {
			if (c == '_') {
				return false;
			}
		}
		return true;
	}

	/*
	 * Une fonction pour jouer une partie, après qu'un mot ait été choisi. Cette
	 * fonction permettra seulement un nombre limité d'essais.
	 */
	public static void playGame(InputStream is, PrintStream ps, Scanner scanner) {

		while (nb_vies > 0 && !guessed()) {

			ps.println("\n\nVous avez " + nb_vies + " vies");
			ps.println(mot_a_trouver);

			ps.print("\n\nEcris une lettre : ");
			String input = scanner.nextLine();

			while (input.isEmpty() || input.length() != 1 || (input.charAt(0) < 'a' || input.charAt(0) > 'z')
					&& (input.charAt(0) < 'A' || input.charAt(0) > 'Z')) {

				ps.print("Ecris une lettre : \n");
				input = scanner.nextLine();

			}

			// transforme les lettres majuscules saisis en minuscules
			char lettre = input.charAt(0);
			if (lettre >= 'A' && lettre <= 'Z') {
				lettre = Character.toLowerCase(lettre);
			}

			boolean valide = false;
			for (int i = 0; i < the_mot.length(); i++) {
				if (the_mot.charAt(i) == lettre) {
					mot_a_trouver[i] = lettre;
					valide = true;
				}
			}
			if (!valide) {
				nb_vies--;
				ps.println("'" + lettre + "' n'est pas dans le mot\n");
			}

		}
		if (guessed()) {
			ps.println("BRAVO ! --> Le mot était : " + the_mot);
		} else {
			ps.println("PERDU ! --> Le mot était : " + the_mot);
		}

	}

}
