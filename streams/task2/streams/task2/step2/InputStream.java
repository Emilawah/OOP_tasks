package streams.task2.step2;

import java.io.EOFException;

/*
 * This is an implementation of an input stream that
 * is based on the use of a byte array to hold the 
 * bytes that will be read via the method "read" on this stream. 
 * The close operation has not effect on this implementation,
 * it is a non-operation (no-op). 
 * 
 * @author Pr. Olivier Gruber.
 */

public class InputStream {

	private int size;
	private int idx;
	private byte[] buffer;

	/**
	 * Constructs an input stream from the given output stream
	 */
	public InputStream(OutputStream s) {
		size = s.getSize();
		idx = 0;
		buffer = s.getBytes();
	}

	/**
	 * @return the number of available bytes in this input stream. Returning 0 means
	 *         that are no available bytes but some might become available later.
	 *         Returning -1 indicates the end of the stream.
	 */
	public int available() {
		if (idx >= size) {
			return -1;
		} else {
			return size - idx;
		}
	}

	/**
	 * Reads the next byte from this input stream. <br>
	 * 
	 * @return the read byte
	 * @throws IllegalStateException if there are no more byte to read
	 */
	public byte read() {
		if (available() == -1) {
			throw new IllegalStateException("Erreur, fin du flux");
		}
		byte value = buffer[idx];
		idx++;
		return value;
	}
}
