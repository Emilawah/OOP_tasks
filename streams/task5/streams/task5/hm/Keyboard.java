package streams.task5.hm;

import oop.streams.InputStream;

class Keyboard {
	
	InputStream m_is;
	
	Keyboard(oop.streams.InputStream is) {
		this.m_is = is;
	}

	char readChar() {
		return (char) m_is.read();
	}

	String readLine() {
		StringBuilder s = new StringBuilder();
		while(m_is.available() != 0) {
			s.append(readChar());
		}
		return s.toString();
	}
}
