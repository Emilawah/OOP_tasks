package streams.task5.hm;

public class HangedMan {

	private char[] mot_a_trouver;
	private char[] the_mot;
	public int nb_vies;

	/*
	 * Imposed field to hold the secret words.
	 */

	private char[] words[];

	/*
	 * The two constructors.
	 */

	public HangedMan(char[] words[]) {
		this.words = words;

	}

	public HangedMan(String words[]) {
		this.words = new char[words.length][];
		for (int i = 0; i < words.length; i++) {
			this.words[i] = words[i].toCharArray();
		}
	}

	/*
	 * Initializes a new game with nth word.
	 */
	public void newGame(int n) {
		// on transforme le string en tableau de caractères
		this.the_mot = words[n];
		this.mot_a_trouver = new char[the_mot.length];
		for (int i = 0; i < the_mot.length; i++) {
			mot_a_trouver[i] = '-';
		}
		this.nb_vies = 2 * the_mot.length;

	}
	
	public void newGame(int n, int ntries) {
		this.nb_vies = ntries;
		this.the_mot = words[n];
		this.mot_a_trouver = new char[the_mot.length];
		for (int i = 0; i < the_mot.length; i++) {
			mot_a_trouver[i] = '-';
		}
		}

	/*
	 * Once a game has been initialized, this method is used to propose a character
	 */
	public void play(char c) {
		boolean dansMot = false;

		for (int i = 0; i < the_mot.length; i++) {

			if (the_mot[i] == c) {

				if (mot_a_trouver[i] != c) {
					mot_a_trouver[i] = c;
					dansMot = true;
				}
			}
		}
		if (!dansMot) {
			nb_vies--;
		}
	}

	/*
	 * Returns a string that corresponds to the current guessed letters. For
	 * example: -a-a-a for the secret word "banana" and the letter 'a' that has been
	 * guessed correctly.
	 */
	public String guessed() {
		return new String(mot_a_trouver);
	}

	/*
	 * Returns true if the player has won. false otherwise.
	 */
	public boolean won() {
		for (char c : mot_a_trouver) {
			if (c == '-') {
				return false;
			}
		}
		return true;

	}

	/*
	 * Returns true if the player has lost. false otherwise.
	 */
	public boolean lost() {
		return nb_vies <= 0;
	}
}
