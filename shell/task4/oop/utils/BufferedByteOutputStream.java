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
   * The given capacity is the size of each chunk, that is,
   * the length of the byte array used by each chunk to
   * buffer written bytes until they can be written to
   * the given output stream.
   */
  public BufferedByteOutputStream(int capacity, OutputStream os) {
    throw new RuntimeException("NYI");
  }

  @Override
  public void set(Listener l) {
    throw new RuntimeException("NYI");
  }

  @Override
  public void close() {
    throw new RuntimeException("NYI");
  }

  @Override
  public boolean closed() {
    throw new RuntimeException("NYI");
  }

  @Override
  public boolean available() {
    throw new RuntimeException("NYI");
  }

  @Override
  public void write(byte bits) {
    throw new RuntimeException("NYI");
  }

  @Override
  public int write(byte[] bytes, int offset, int length) {
    throw new RuntimeException("NYI");
  }


}
