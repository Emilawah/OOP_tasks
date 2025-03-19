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

public class Environment {

	private Variable head;

	Environment() {
		this.head = null;
	}

	public class Variable {

		String name;
		String value;
		Variable next;

		Variable(String name, String value, Variable next) {
			this.name = name;
			this.value = value;
			this.next = next;
		}
	}

	/*
	 * List the names of all the known variables.
	 */
	String[] listNames() {
		String[] names = new String[0];
		Variable var = head;
		int i = 0;
		while (var != null) {
			String[] newNames = new String[i + 1];

			for (int k = 0; k < i; k++) {
				newNames[k] = names[k];
			}

			newNames[i] = var.name;
			names = newNames;
			var = var.next;
			i++;
		}
		return names;
	}

	/*
	 * Gets the value of the variable with the given name
	 */
	String get(String name) {
		Variable var = head;
		while (var != null) {
			if (var.name.equals(name)) {
				return var.value;
			}
			var = var.next;
		}
		return "";
	}

	/*
	 * Delete the variable with the given name
	 */
	String del(String name) {
		Variable var = head;
		Variable prev = null;

		while (var != null) {
			if (var.name.equals(name)) {
				if (prev == null) {
					head = var.next;
				} else {
					prev.next = var.next;
				}
				return var.value;
			}
			prev = var;
			var = var.next;
		}
		return null;
	}

	/*
	 * Create or update a variable with the given name with the given value.
	 */
	void put(String name, String val) {
		Variable var = head;
		while (var != null) {
			// si la variable existe déja dans le dico (changer valeur):
			if (var.name.equals(name)) {
				var.value = val;
				return;
			}
			var = var.next;
		}
		// si la variable n'existe pas dans le dico, l'ajouter en tête
		head = new Variable(name, val, head);

	}

}