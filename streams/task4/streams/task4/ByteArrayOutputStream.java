package streams.task4;

import oop.streams.OutputStream;

public class ByteArrayOutputStream implements OutputStream{

	protected byte[] buffer;
	protected int idx;
	protected int size;

	
	public ByteArrayOutputStream(byte buffer[], int offset, int length) {
		this.buffer = buffer;
		this.idx = offset;
		this.size = length;
	}

	public ByteArrayOutputStream(byte buffer[]) {
		this(buffer,0,buffer.length);
	}

	/**
	 * Returns an array containing the bytes written to this output stream.
	 */
	public byte[] getBytes() {
		return buffer;
	}

	/**
	 * Fills up the given array, starting at the given offset, with the bytes
	 * contained in this stream.
	 */
	public void getBytes(byte bytes[], int offset) {
		for(int i =  offset ; i< bytes.length ; i++) {
			buffer[i] = bytes[i];
		}
	}			

	/**
	 * @return the current size of this output stream, that is, the number of bytes
	 *         that have been written.
	 */
	public int getSize() {
		return idx;
	}

	@Override
	public int available() {
		if (size - idx +1 == 0) {
			return 0;
		}
		if (idx < size) {
			return size - idx;
		}  
		return -1;

	}

	@Override
	public void write(byte value) {
		if(available()==-1 || available()==0) {
			throw new IllegalStateException("The end of the stream has been reached");
		}
		if (idx >= size) {
			byte[] new_buffer = new byte[buffer.length + idx];
 
			for (int i = 0; i < idx; i++) {
				new_buffer[i] = buffer[i];
			}
			
			buffer = new_buffer;
		}
			
		buffer[idx] = value;
		idx++;

		
	}
}
