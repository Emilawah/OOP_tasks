package streams.task4;


public class DynamicByteArrayOutputStream extends ByteArrayOutputStream {
	/**
	 * Constructs an output stream with an initial capacity of the given number of
	 * bytes. The second parameter gives the number of bytes that the internal will
	 * be grown by each time it fills up.
	 */
	private int delta;

	public DynamicByteArrayOutputStream(int capacity, int delta) {
		super(new byte[capacity]);
		this.delta = delta;

	}

	public void resize() {

		byte[] new_buffer = new byte[size + delta];

		for (int i = 0; i < size; i++) {
			new_buffer[i] = buffer[i];
		}
		buffer = new_buffer;

		size = size + delta;
	}

	@Override
	public int available() {
		if (size - idx == 0) {
			resize();
		}
		return size - idx;
	}

	@Override
	public void write(byte value) {

		if (available() < 0) {
			throw new IllegalStateException("End of Stream");
		}

		buffer[idx] = value;
		idx++;

	}

}
