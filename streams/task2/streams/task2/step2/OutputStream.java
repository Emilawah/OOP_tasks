package streams.task2.step2;

/*
 * This is an implementation of an output stream that
 * is based on the use of a byte array to hold the 
 * bytes that will be written via the method "write" on this stream. 
 * The method "write" must never fail, meaning the internal array
 * must be grown as necessary.
 * The close operation has not effect on this implementation,
 * it is a non-operation (no-op). 
 * 
 * @author Pr. Olivier Gruber.
 */

public class OutputStream {

	private int tab_length;
	private int delta;
	private int idx;

	private byte[] buffer;
	private byte[] new_buffer;

	/**
	 * Constructs an output stream with an initial capacity of the given number of
	 * bytes.
	 * 
	 * @param capacity is the initial length of the byte array
	 * @param delta    is the number of bytes to grow the array by.
	 */
	public OutputStream(int capacity, int delta) {
		this.tab_length = capacity;
		this.delta = delta;
		this.buffer = new byte[tab_length];
		this.idx = 0;
	}

	/**
	 * Returns an array containing the bytes written to this output stream.
	 */
	public byte[] getBytes() {
		return buffer;
	}

	/**
	 * @return the number of bytes written to this output stream,
	 */
	public int getSize() {
		return idx;

	}

	/**
	 * Writes the given byte into this stream. This method must never fail, the
	 * array must be grown if full, by adding 64 bytes each time the array is grown.
	 */
	public void write(byte value) {
		if (idx >= tab_length) {
			tab_length = tab_length + delta;
			new_buffer = new byte[tab_length];

			for (int i = 0; i < idx; i++) {
				new_buffer[i] = buffer[i];
			}
			
			buffer = new_buffer;
		}
		
		buffer[idx] = value;
		idx++;

	}
}
