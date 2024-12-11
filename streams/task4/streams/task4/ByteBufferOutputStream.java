package streams.task4;

public class ByteBufferOutputStream extends ByteArrayOutputStream {

	private int chunk_size;
	private Chunk head;
	private Chunk tail;
	private int pos;

	public ByteBufferOutputStream(int chunk_size) {
		super(new byte[chunk_size]);
		this.chunk_size = chunk_size;
		this.head = null;
		this.tail = head;
		this.pos = 0;
	}

	private void addChunk() {
		Chunk new_chunk = new Chunk(chunk_size);
		new_chunk.set_bytes(buffer);
		if (head == null) {
			head = new_chunk;
			tail = head;
			idx = 0;
			
		} else {
			
			tail.set_next(new_chunk);
			tail = tail.get_next();
			idx = 0;
			
		}

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

//	@Override
//	public void write(byte value) {
//		if (available() < 0) {
//			throw new IllegalStateException("End of the stream");
//		}
//
//		buffer[idx] = value;
//		idx++; // index pour le buffer temp
//		pos++; // index pour le buffer entier 
//	}
}
