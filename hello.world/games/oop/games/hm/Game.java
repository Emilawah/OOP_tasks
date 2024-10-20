package oop.games.hm;

import java.util.Random;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;

public class Game {

	private PrintStream ps;
	private Keyboard kbd;
	private HangedMan hm;
	private Random rand;
	private int nwords; // nombre de mots dans notre dico
	private int n; // le nombre aléatoire (index du dico) qui sera genéré

	public Game(String[] args, InputStream in, PrintStream out) {

		this.hm = new HangedMan(args);
		this.rand = new Random();
		this.nwords = args.length;

	}

	public void play(InputStream in, PrintStream out) throws IOException {
		char c;

		this.ps = out;
		this.kbd = new Keyboard(in, out);

		boolean rejouer = true;

		while (rejouer) {
			ps.println("Bienvenue dans le jeu du Pendu !\n\n");
			n = rand.nextInt(nwords);
			hm.newGame(n);

			// tant que on a pas perdu (vies > 0) et qu'on a pas gagné (mot non trouvé ou
			// vie =0)
			while (!hm.won() && !hm.lost()) {
				ps.println("Vous avez " + hm.nb_vies + " vies\n\n");
				
				c = kbd.read("Ecris une lettre : ");


				hm.play(c);
				ps.println(hm.guessed());

			}
			// si on a gagné la partie, afficher un message de féliciations
			if (hm.won()) {
				ps.println("BRAVO vous avez trouvé le mot !\n");

			}
			// si on a perdu la partie, on affiche un message de défaite
			if (hm.lost()) {
				ps.println("PERDU !\n");

			}
			
			/// On propose au joueur de rejouer 

			char replay = kbd.read("Voulez vous rejouer ? (y/n) : ");

			while (replay != 'y' && replay != 'n') {
				replay = kbd.read("Saisie invalide, Voulez vous rejouer ? (y/n) : ");
			}
			if (replay == 'n') {
				rejouer = false;
				ps.println("Au revoir !\n");
			}
		}
		return;

	}
}
