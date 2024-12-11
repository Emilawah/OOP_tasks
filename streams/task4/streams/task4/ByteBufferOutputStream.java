package streams.task4;

public class ByteBufferOutputStream extends ByteArrayOutputStream {

	private Chunk head;
	private Chunk tail;
	private int pos;


	public ByteBufferOutputStream(int chunk_size) {
		super(new byte[chunk_size]);
		this.head = null;
		this.tail = null;
		this.pos = 0;

	}

	private void addChunk() {

		Chunk new_chunk = new Chunk(buffer);

		if (head == null) {
			head = new_chunk;
			tail = head;

		} else {

			tail.set_next(new_chunk);
			tail.get_next().set_next(null);
			tail = tail.get_next();
		}
		
		buffer = new byte[buffer.length];
		idx = 0;
		
	}

	@Override
	public byte[] getBytes() {
		if(idx > 0) {
			
			byte[] temp = new byte[idx];
			
			for(int i = 0; i < idx ; i++) {
				temp[i] = buffer[i];
			}
			
			Chunk new_chunk = new Chunk(temp);
			
			if(head == null) {
				head = new_chunk;
				tail = new_chunk;
			}else{
				tail.set_next(new_chunk);
				tail = new_chunk;
			}
 		}
		
		byte[] bytes = new byte[pos];
		Chunk c = head;
		int index = 0;
		while(c != null) {
			byte[] currentBytes = c.get_byte_c();
			for(int i = 0; i < currentBytes.length && index < bytes.length; i++) {
				bytes[index++] = currentBytes[i];
			}
			c = c.get_next();
 		}
		return bytes;
//		byte tab[] = new byte[idx];
//		int i = 0;
//		for (int k = 0; k < idx - pos; k++) {
//			if (head == null) {
//				return tab;
//			}
//			if (i >= chunk_size) {
//				head.get_next();
//				i = 0;
//			}
//			tab = head.get_byte_c();
//			i++;
//		}
//		for (int l = 0; l < pos; l++) {
//			tab[idx - pos + 1] = bytes[l];
//		}
//		return tab;
	}

	@Override
	public int getSize() {
		return pos;
	}

	@Override
	public int available() {
		if (size - idx == 0) {
			addChunk();
		}
		return size - idx;
	}

	@Override
	public void write(byte value) {
		
		
		buffer[idx] = value;
		idx++;
		pos++;
		if (available() < 0) {
			throw new IllegalStateException("End of Stream");
		}

	}
}
