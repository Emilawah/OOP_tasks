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

import oop.streams.Stream;
import oop.tasks.Task;

public abstract class ByteStream implements Stream {
  protected ByteRing m_ring;

  protected ByteStream(ByteRing ring) {
    throw new RuntimeException("NYI");
  }

  @Override
  public void set(Listener l) {
    throw new RuntimeException("SHOULD IT BE IMPLEMENTED HERE?");
  }

  @Override
  public void close() {
    throw new RuntimeException("SHOULD IT BE IMPLEMENTED HERE?");
  }

  @Override
  public boolean closed() {
    throw new RuntimeException("SHOULD IT BE IMPLEMENTED HERE?");
  }

  @Override
  public boolean available() {
    throw new RuntimeException("SHOULD IT BE IMPLEMENTED HERE?");
  }
}
