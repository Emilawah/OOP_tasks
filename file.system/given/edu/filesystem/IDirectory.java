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
 * A directory may contain directories and files.
 * Therefore, directories form a tree of directories.
 * 
 * In the following, a directory is valid as long as it is not 
 * removed from its parent directory and no requested operation
 * on that directory failed for whatever reason. In other words,
 * if an operation on a directory cannot complete **normally**, 
 * the directory must become invalid. Once invalid, a directory 
 * remains invalid and should no longer be used.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 *
 */
public interface IDirectory {

  /**
   * @return true if this directory was not removed from its parent.
   *         false otherwise.
   */
  public boolean valid();

  /**
   * @return the file system of this directory, 
   *         even if this directory is no longer valid.
   */
  public IFileSystem getFileSystem();

  /**
   * @return the parent directory of this directory, 
   *         even if this directory is no longer valid.
   */
  public IDirectory directory();

  /**
   * @return the name of this directory, 
   *         even if this directory is no longer valid.
   */
  public String name();

  /**
   * @return the full path to this directory, 
   *         even if this directory is no longer valid.
   *         The full path is the full path of the parent directory 
   *         plus the name of this directory.
   */
  public String path();

  /**
   * Searches for a directory with the given name.
   * @param the name of the wanted directory (not a path)
   * @return the directory with the given name, if found,
   *         null otherwise.
   */
  public IDirectory dir(String name);

  /**
   * @return an array with all the sub-directories found
   *         in this directory.
   */
  public IDirectory[] dirs();

  /**
   * Searches for a file with the given name (not a path).
   * @param the name of the wanted file (not a path)
   * @return the file with the given name, if found,
   *         null otherwise.
   */
  public IFile file(String name);

  /**
   * @return an array with all the files found in this
   *         directory.
   */
  public IFile[] files();

  /**
   * Creates a directory as a child of this directory if there is no name conflict.
   * Name conflict: a directory may not have the same name as a file,
   *                two directories may not have the same name.
   * @param desired name
   * @return the created directory or null if the directory could not
   *         be created.
   */
  public IDirectory mkdir(String name);

  /**
   * Removes a directory with the given name, only if the directory is empty.
   * the removed directory becomes invalid.
   * @param name
   * @return true if the directory was found and removed,
   *         false otherwise.
   */
  public boolean rmdir(String name);

  /**
   * Create a file with the given name, if it is possible and 
   * it does not already exist.
   * @param the name of the wanted file (not a path)
   * @return the created file or null if the file could not be created
   *         or it already exists.
   */
  public IFile touch(String name);

  /**
   * Removes the file by the given name.
   * @param name
   * @return true if the file was found and removed,
   *         false otherwise.
   */
  public boolean rm(String name);
  
  /**
   * Moves and renames this directory.
   * Moving a directory where it is already is acceptable. 
   * @return true if the operation was possible, false otherwise.
   */
  public boolean move(IDirectory dst, String name);

}
