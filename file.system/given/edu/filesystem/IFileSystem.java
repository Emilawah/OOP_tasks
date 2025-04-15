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
 * A file system is a tree of directories and files.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 *
 */
public interface IFileSystem {
  
  public static final char sep = '/';
    
  public String name();

  /*
   * Returns the root directory for this file system.
   * That root directory has no name, if your request 
   * its name, you will get the empty string ("").
   */
  public abstract IDirectory root();
  
}
