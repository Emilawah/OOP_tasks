package streams.task5.hm;

import oop.streams.OutputStream;

class PrintStream {

	OutputStream m_os;
	
	PrintStream(oop.streams.OutputStream os) {
		this.m_os = os;
	}

	void print(String s) {
		for(int i = 0 ; i< s.length() ; i++) {
			m_os.write((byte)s.charAt(i));
		}
	}

	void println(String s) {
		print(s + System.lineSeparator());
	}
}
