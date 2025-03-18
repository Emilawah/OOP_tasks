package oop.utils;

public class Chunk {
	byte[] data;
    int size;
    Chunk next;
    int pos_chunk;

    Chunk(int capacity) {
        this.data = new byte[capacity];
        this.next = null;
    }
    
    public boolean chunkFull() {
    	return size >= data.length;
    }
    
    

}
