package streams.task5.hm;

import java.io.IOException;


import oop.streams.InputStream;

import oop.streams.OutputStream;

public class Script {
	private HangedMan hm;
	private PrintStream ps;
	private InputStream my_is;

	public Script(String words[], InputStream is, OutputStream os) throws IOException {
		this.my_is = is;
		this.ps = new PrintStream(os);
		this.hm = new HangedMan(words);

	}

	

	/*
	 * Plays the script.
	 */
	public void play() {
		String s;
		char c;
		CharReader cr = new CharReader(my_is);
		s=cr.readLine();
		
		int idx_word = (int)s.charAt(6)-48;
		int ntries = (int)s.charAt(8)-48;
		hm.newGame(idx_word, ntries);
		
		while(!hm.won() && !hm.lost()) {
			s = cr.readLine();
			c= s.charAt(7);
			hm.play(c);
			
		}
		if(hm.won()) {
			ps.print("OK: PASSED\n");
		}
		if(hm.lost()) {
			ps.print("OK: FAILED\n");
		}
	}

	public HangedMan game() {
		return hm;
	}
}