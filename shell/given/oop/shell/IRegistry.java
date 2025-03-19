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
package oop.shell;

import oop.streams.InputStream;
import oop.streams.OutputStream;

/* 
 * This is the registry for the programs that can be
 * launched from the shell command line.
 * This registry holds (name, factory) pairs, where
 * the name must be unique and the factory provides
 * a way to create new instances of the program
 * corresponding to the name.
 */
public interface IRegistry {

  public interface IFactory {
    /*
     * The name of the factory, corresponding to the name 
     * used in the shell command line to launch a new instance
     * of this program.
     */
    String name(); 
    
    /*
     * This is the class name to be instantiated.
     * The default factory requires that class to define
     * the following method:
     *     public static void main(java.lang.String[] args, 
     *                             oop.utils.PrintStream ps, 
     *                             oop.utils.InputStream is);
     */
    String className();
    
    /*
     * This actually creates a new "program" and launches it
     * with the given arguments, executing on a new task, 
     * the one being returned. The provided listener
     * allows to be notified once the task completes. 
     */
    Runnable launch(String args[], InputStream is, OutputStream ps);
  }
  
  /*
   * Lists the known names.
   */
  String[] list();
  
  /*
   * Looks up the factory registered with the given name, 
   * if it exists. Return null if the name is unknown.
   */
  IFactory lookup(String name);
  
  /*
   * Registers a new pair (name, factory), 
   * only if the name has not been already registered.
   * Returns true if the registration was accepted,
   * false otherwise.
   */
  boolean register(String name, IFactory factory);
  
  /*
   * Unregisters the pair (name,factory) corresponding
   * to the given name. Returns true if the name was
   * found and the operation was successful, false otherwise.
   */
  boolean unregister(String name);
  
}
