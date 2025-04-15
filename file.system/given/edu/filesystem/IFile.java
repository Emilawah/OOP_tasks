/*
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
 *  Created on: November, 2017
 *      Author: Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 */
package edu.filesystem;

/**
 * A file is a sequence of bytes that can be read or written.
 * 
 * A file belongs to a directory, always, when valid. When a file
 * is removed from its directory, it becomes invalid and most methods
 * throw an illegal-state exception. Once invalid, a file remains invalid
 * and should no longer be used. References to an invalid file should be
 * cut. Also, a file may become invalid if any operation on that file
 * cannot complete **normally**.
 * 
 * A file maintains a current position where to read a byte value 
 * from or write a byte value to. That position is automatically
 * incremented when reading or writing, but it can be also manually
 * set to any position. 
 * It is legal to set the current position beyond the current end 
 * of the file, in effect, growing the file, when new bytes are written 
 * at that position. Correspondingly, a read will indicate the end-
 * of-file condition when reaching the end of the file while a write 
 * will grow the file. 
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public interface IFile {

  /**
   * @return true if this file was not removed from its parent.
   */
  public boolean valid();

  /**
   * @return the parent of this file, if this file is valid, null otherwise.
   */
  public IDirectory directory();

  /**
   * @return the name of this file, even if this file is no longer valid.
   */
  public String name();

  /**
   *  @return the full path to this file, that this file is valid or not.
   *          The path returned by an invalid file is the one it had when
   *          removed from its parent.
   */
  public String path();

  /**
   * @return the size of this file in bytes
   *         the size of an invalid file is zero
   */
  public int size();

  /**
   * @return the number of available bytes to read
   *         from the current position, see seek
   *         That number is zero for an invalid file.
   */
  public int available();

  /**
   * @return the byte at the current position and increments
   *         that position by one.
   * @throws EndOfFileException if the end of file 
   *         has been reached or if this file is invalid.
   * @throws IllegalArgumentException if the file is invalid
   */
  public byte read();

  /**
   * If possible, writes the given byte at the current position 
   * and increments that position by one.
   * @throws EndOfFileException if the file cannot be grown.
   * @throws IllegalArgumentException if the file is invalid
   */
  public void write(byte val);

  /**
   * A file maintains a current position to read from
   * or to write at. This method allows to change 
   * that position at the given offset, even passed
   * the current end of file, which effectively grows
   * the file. 
   * @throws IllegalArgumentException if the given offset
   *         is not an acceptable value, like a negative
   *         offset or an offset that is larger than the 
   *         largest size managed by this file system. 
   */
  public void seek(int offset);

  /**
   * Moves and renames this file, from its current directory
   * to the given directory, with the given name in that destination
   * directory.
   * @return true if the renaming was possible, false otherwise.
   */
  public boolean move(IDirectory dst, String name);

}
