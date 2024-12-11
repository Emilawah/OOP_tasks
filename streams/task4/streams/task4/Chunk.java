package streams.task4;

public class Chunk {
	
	private byte[] bytes; // the array of bytes
	private Chunk next;   // the next chunk
	
	public Chunk(int capacity) {
		this.bytes = new byte[capacity];
		this.next = null;
	}
	
	public Chunk get_next() {
		return next;
	}
	
	public byte[] get_byte_c() {
		return bytes;
	}
	
	public void set_next(Chunk next) {
		this.next = next;
	}
	
	public void set_bytes(byte[] bytes) {
		for(int i = 0 ; i < bytes.length ; i++) {
			this.bytes[i] = bytes[i];
		}
	}
}
