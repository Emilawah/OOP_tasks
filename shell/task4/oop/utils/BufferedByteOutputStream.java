/*
 *  Copyright (C) Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package oop.utils;

import oop.streams.OutputStream;

/*
 * An instance of this class wraps an output stream
 * and offers a buffered output stream, that is, 
 * an output stream that always accepts to write all 
 * the given bytes, buffering them if necessary.
 * 
 * The buffering scheme is a list of chunks, each chunk
 * being of the capacity given to the constructor. The 
 * written bytes are buffered until they can be written
 * through the wrapped output stream.
 */

public class BufferedByteOutputStream implements OutputStream {

	/*
	 * The given capacity is the size of each chunk, that is, the length of the byte
	 * array used by each chunk to buffer written bytes until they can be written to
	 * the given output stream.
	 */

	private Listener listener;
	private OutputStream m_os;
	private boolean isClosed = false;
	private int capacity;
	private Chunk head;
	private Chunk tail;

	public BufferedByteOutputStream(int capacity, OutputStream os) {
		this.capacity = capacity;
		this.m_os = os;
		this.head = new Chunk(capacity);
		this.tail = head;

	}

	@Override
	public void set(Listener l) {
		m_os.set(l);

	}

	@Override
	public void close() {

		if (!isClosed) {
			vider();
			isClosed = true;
			m_os.close();
		}
		if (listener != null) {
			listener.closed(this);
		}
	}

	@Override
	public boolean closed() {
		return isClosed;
	}

	@Override
	public boolean available() {
		if (listener != null) {
			listener.available(this);
		}
		return !head.chunkFull();

	}

	@Override
	public void write(byte bits) {
		if (closed()) {
			throw new IllegalStateException("Stream is closed");
		}

		if (tail.chunkFull()) {
			Chunk new_chunk = new Chunk(capacity);
			tail.next = new_chunk;
			tail = new_chunk;
		}
		tail.data[tail.size++] = bits;

	}

	@Override
	public int write(byte[] bytes, int offset, int length) {

		if (closed()) {
			throw new IllegalStateException("Stream is closed");
		}
		int nb_bytes = 0;
		for (int i = offset; i < offset + length; i++) {
			write(bytes[i]);
			nb_bytes++;
		}
		return nb_bytes;
	}

	public void vider() {
		while (head != null) {
			if (head.size > 0) {
				m_os.write(head.data, 0, head.size);
			}
			head = head.next;
		}

		tail = new Chunk(capacity);
		head = tail;

	}

	public boolean isFull() {
		return tail.chunkFull();
	}
}
