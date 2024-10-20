package oop.games.hm;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class Keyboard {

	PrintStream output;
	InputStream input;
	Reader reader;
	BufferedReader buffer;

	public Keyboard(InputStream in, PrintStream out) {
		this.output = out;
		this.input = in;
		this.reader = new InputStreamReader(in);

	}

	/*
	 * If given a message, this method prints the message and reads one character,
	 * the first of the line entered by the player. It will be used to read the
	 * letter proposals and the character to know if the player want to play again
	 * or not, that is, the character (y/n).
	 */
	public char read(String msg) throws IOException {

		output.print(msg);
		BufferedReader buffer = new BufferedReader(reader);

		return (char) buffer.read();
	}
}
