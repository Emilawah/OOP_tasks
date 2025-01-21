package streams.task5.hm;

import oop.streams.InputStream;

public class CharReader {

	private InputStream m_is;

	public CharReader(InputStream is) {
		this.m_is = is;
	}

	/*
	 * Reads a line, that is, a sequence of characters terminated with a '\n', but
	 * the '\n' is not included in the returned string.
	 */
	public String readLine() {
		String s="";
		char c=(char)m_is.read();
		while(c!='\n') {
			s+=c;
			c=(char)m_is.read();
		}
		return s;
	}

	/*
	 * Read a char that is not '\n'.
	 */
	public char readChar() {
		char c = (char)m_is.read();
		while(c=='\n') {
			c=(char)m_is.read();
		}
		return c;
		}
}