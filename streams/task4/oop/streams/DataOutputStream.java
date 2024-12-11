package oop.streams;


/**
 * This is an data output stream that wraps an output stream of bytes, allowing
 * to write different Java types such as integers, floats, and strings. The
 * companion class is the class DataInputStream.
 * 
 * @author Pr. Olivier Gruber.
 */

public class DataOutputStream {
	OutputStream os;

	public DataOutputStream(OutputStream os) {
		this.os = os;
	}

	/**
	 * Writes the given double value. Encoded over 8-bytes, with big-endian
	 * encoding.
	 */
	public void writeDouble(double value) {
		writeLong(Double.doubleToLongBits(value));
	}

	/**
	 * Writes the given float value. Encoded over 4-bytes, with big-endian encoding.
	 */
	public void writeFloat(float value) {
		writeInt(Float.floatToIntBits(value));
	}

	/**
	 * Writes the given long value. Encoded over 8-bytes, with big-endian encoding.
	 */
	public void writeLong(long value) {
		for (int i = 7; i >= 0; i--) {
			writeByte((byte) ((value >> i * 8) & 0xFF));
		}
	}

	/**
	 * Writes the given integer value Encoded over 4-bytes, with big-endian
	 * encoding.
	 */
	public void writeInt(int value) {
		for (int i = 3; i >= 0; i--) {
			writeByte((byte) ((value >> i * 8) & 0xFF));
		}
	}

	/**
	 * Writes the given short value Encoded over 2-bytes, with big-endian encoding.
	 */
	public void writeShort(short value) {
		writeByte((byte) ((value >> 8) & 0xFF));
		writeByte((byte) (value & 0xFF));
	}

	/**
	 * Writes the given byte value
	 */
	public void writeByte(byte value) {
		os.write(value);
	}

	/**
	 * Writes the given boolean value Encoded over 1-bytes.
	 */
	public void writeBoolean(boolean value) {
		if (value) {
			writeByte((byte) 1);
		} else {
			writeByte((byte) 0);
		}
	}

	/**
	 * Writes the given character as UTF-8 encoded character.
	 */
	public void writeChar(char c) {
		if((c <= 0x0001) && (c <= 0x007F)){
			writeByte((byte)c);
		}
		else if (c > 0x07FF) {		// on encode le caractère en 3 octets
	        writeByte((byte)(0xE0 | ((c >> 12) & 0x0F)));
	        writeByte((byte)(0x80 | ((c >> 6) & 0x3F)));   
	        writeByte((byte)(0x80 | ((c >> 0) & 0x3F)));
	      } else {						// on encode le caractère en 2 octets
	        writeByte((byte)(0xC0 | ((c >> 6) & 0x1F)));
	        writeByte((byte)(0x80 | ((c >> 0) & 0x3F)));
	      }
	}

	/**
	 * Writes the given string as a sequence of UTF-8 encoded characters.
	 */
//	public void writeUTF(String s) {
//		int length = s.length();
//		for (int i = 0; i < length; i++) {
//			char c = s.charAt(i);
//			if ((c >= 0x0001) && (c <= 0x007F)) { // Entre 0 et 127 (ASCII)
//				writeByte((byte) c);
//			} else if ((c > 0x0080) && (c >= 0x07FF)) { // Pour des caracteres codés sur 2 octets
//				writeByte((byte) (0xC0 | ((c >> 6) & 0x1F)));
//				writeByte((byte) (0x80 | (c & 0x3F)));
//			} else if ((c > 0x0800) && (c <= 0xFFFF)) { // Pour des caractères codés sur 3 octets
//				writeByte((byte) (0xE0 | (c >> 12) & 0x0F));
//				writeByte((byte) (0x80 | (c >> 6) & 0x3F));
//				writeByte((byte) (0x80 | (c & 0x3F)));
//			}
//		}
//	}
	public void writeUTF(String s) {
		int length = s.length();
		for (int i = 0; i < length; i++) {
			writeChar(s.charAt(i));
		}
	}

}
