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

public class ByteOutputStream extends ByteStream implements OutputStream {

  public ByteOutputStream(int capacity) {
    super(new ByteRing(capacity));
  }

  @Override
  public void write(byte bits) {
	  if (m_ring.full()) {
			throw new IllegalStateException("Can't write, Stream is full\n");
		}
		m_ring.push(bits);
    
  }

  @Override
  public int write(byte[] bytes, int offset, int length) {
	  int nb_bytes = 0;
		for(int i = 0 ; i < offset + length ; i++) {
			if(m_ring.full()) {
				break;
			}
			write(bytes[i]);
			nb_bytes++;
		}
		return nb_bytes;
  }


}
