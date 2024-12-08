package streams.task4;

import oop.streams.InputStream;

public class ByteArrayInputStream implements InputStream {

	protected int idx;
	protected int size;
	protected byte[] buffer;

	public ByteArrayInputStream(byte buffer[], int offset, int length) {
		this.buffer = buffer;
		this.idx = offset;
		this.size = length;
	}

	public ByteArrayInputStream(byte buffer[]) {
		this(buffer,0,buffer.length);
	}

	public ByteArrayInputStream(ByteArrayOutputStream s) {
		this(s.getBytes());
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
	public byte read() {
		if (available() == -1) {
			throw new IllegalStateException("The end of the stream has been reached");
		}
		byte value = buffer[idx];
		idx++;
		return value;
	}

}
