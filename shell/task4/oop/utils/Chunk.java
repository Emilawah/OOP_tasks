package oop.utils;

public class Chunk {
	byte[] data;
    int size;
    Chunk next;
    int pos_chunk;
    Chunk head;

    Chunk(int capacity) {
        this.data = new byte[capacity];
        this.size = 0;
        this.next = null;
        this.pos_chunk = 0;
    }
    
    public boolean isFull() {
    	return size >= data.length;
    }
    
    

}
