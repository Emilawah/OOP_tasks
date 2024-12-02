package streams.task3;


import streams.task2.step2.InputStream;

/**
 * This is an data input stream that wraps an input stream of bytes, allowing to
 * read different Java types such as integers, floats, and strings. The
 * companion class is the class DataOutputStream.
 * 
 * @author Pr. Olivier Gruber.
 */

public class DataInputStream {
	InputStream is;

	DataInputStream(InputStream is) {
		this.is = is;
	}

	/**
	 * @return true if the end of the stream has been reached, false otherwise.
	 */
	public boolean endOfStream() {
		return is.available() == -1;
	}

	/**
	 * @return a double value that was encoded over 8-bytes, with big-endian
	 *         encoding.
	 */
	public double readDouble() {

		int b1 = readByte() & 0xFF;
		int b2 = readByte() & 0xFF;
		int b3 = readByte() & 0xFF;
		int b4 = readByte() & 0xFF;
		int b5 = readByte() & 0xFF;
		int b6 = readByte() & 0xFF;
		int b7 = readByte() & 0xFF;
		int b8 = readByte() & 0xFF;
		return Double.longBitsToDouble(
				(b1 << 56) | (b2 << 48) | (b3 << 40) | (b4 << 32) | (b5 << 24) | (b6 << 16) | (b7 << 8) | b8);
	}

	/**
	 * @return a float value that was encoded over 4-bytes, with big-endian
	 *         encoding.
	 */
	public float readFloat() {
		int b1 = readByte() & 0xFF;
		int b2 = readByte() & 0xFF;
		int b3 = readByte() & 0xFF;
		int b4 = readByte() & 0xFF;
		int value = ((b1 << 24) | (b2 << 16) | (b3 << 8) | b4);
		return Float.intBitsToFloat(value);

	}

	/**
	 * @return a long value that was encoded over 8-bytes, with big-endian encoding.
	 */
	public long readLong() {
		int b1 = readByte() & 0xFF;
		int b2 = readByte() & 0xFF;
		int b3 = readByte() & 0xFF;
		int b4 = readByte() & 0xFF;
		int b5 = readByte() & 0xFF;
		int b6 = readByte() & 0xFF;
		int b7 = readByte() & 0xFF;
		int b8 = readByte() & 0xFF;
		int value = ((b1 << 56) | (b2 << 48) | (b3 << 40) | (b4 << 32) | (b5 << 24) | (b6 << 16) | (b7 << 8) | b8);
		return Double.doubleToLongBits(value);

	}

	/**
	 * @returns a signed integer value that was encoded over 4-bytes, with
	 *          big-endian encoding.
	 */
	public int readInt() {
		int b1 = readByte() & 0xFF;
		int b2 = readByte() & 0xFF;
		int b3 = readByte() & 0xFF;
		int b4 = readByte() & 0xFF;
		return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
	}

	/**
	 * @returns a signed short value that was encoded over 2-bytes, with big-endian
	 *          encoding.
	 */
	public short readShort() {
		int b1 = readByte() & 0xFF;
		int b2 = readByte() & 0xFF;
		return (short) ((b1 << 8) | b2 & 0xFF);
	}

	/**
	 * @returns a signed byte value
	 */
	public byte readByte() {
		byte b = is.read();
		return b;
	}

	/**
	 * @returns a boolean value. Encoded over 1-bytes.
	 */
	public boolean readBoolean() {
		return readByte() != 0;
	}

	/**
	 * Reads a UTF-8 encoded character
	 * 
	 * @return the read character
	 * @throws IllegalStateException if the next bytes cannot be decoded as a
	 *                               utf8-encoded character.
	 */
//	public char readChar() {
//		int b1 = readByte() & 0xFF;
//		int b2 = readByte() & 0xFF;
//		return (char) ((b1 << 8) | b2);
//	}
	public char readChar() {
		byte b1 = readByte();
		if((b1 & 0xC0) >> 6 == 0x3) {
			byte b2 = readByte();
			return (char)((b1 & 0x1F) << 6 | (b2 & 0x3F));
			
		}
		if((b1 & 0xE0) >> 5 == 0x7) { 
			byte b2 = readByte();
			byte b3 = readByte();
			return (char)(((b1 << 0x0F) << 12 | (b2 & 0x3F) << 6 | (b3 & 0x3F)));
		}
		return (char) readByte();
	}

	/**
	 * Reads a string of UTF-8 encoded characters.
	 * 
	 * @return the read string.
	 * @throws IllegalStateException if the next bytes cannot be decoded as a
	 *                               utf8-encoded character.
	 */
//	public String readUTF() {
//
//		StringBuilder s = new StringBuilder();
//
//		while (!endOfStream()) {
//			int b1 = readByte() & 0xFF;
//			if ((b1 & 0x80) == 0x00) { // 0xxxxxxx
//				s.append((char) b1);
//			} else if ((b1 & 0xE0) == 0xC0) { // 110xxxxx 10xxxxxx
//				int b2 = readByte() & 0xFF;
//				if ((b2 & 0xC0) != 0x80) {
//					throw new IllegalStateException("Cannot be decoded as a UTF8-encoded character");
//				}
//				s.append((char) (((b1 & 0x1F) << 6) | (b2 & 0x3F)));
//			} else if ((b1 & 0xF0) == 0xE0) { // 1110xxxx 10xxxxxx 10xxxxxx
//				int b2 = readByte() & 0xFF;
//				int b3 = readByte() & 0xFF;
//				if ((b2 & 0xC0) != 0x80 || (b3 & 0xC0) != 0x80) {
//					throw new IllegalStateException("Cannot be decoded as a UTF8-encoded character");
//				}
//				s.append((char) ((b1 & 0x0F) << 12 | (b2 & 0x3F) << 6 | (b3 & 0x3F)));
//
//			} else {
//				throw new IllegalStateException("Cannot be decoded as a UTF8-encoded character");
//			}
//
//		}
//		return s.toString();
//	}
	
	public String readUTF() {
		String s = "";
		char c = readChar();
		while(c != '.') {
			s=s.concat(String.valueOf(c));
			c = readChar();
			
		}
		s=s.concat(String.valueOf(c)); // on ajoute le point
		return s;	
	}


}
