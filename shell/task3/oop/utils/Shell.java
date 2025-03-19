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

import java.lang.reflect.Array;
import java.util.Arrays;

import oop.shell.IRegistry;

import oop.shell.IShell;
import oop.shell.ITerminal;
import oop.shell.ITerminal.Monitor;
import oop.streams.VirtualKeyCodes;

public class Shell implements IShell {
	ITerminal m_term;
	String m_history[];
	Environment env;
	private int history_index = 0;
	private int pos = 0;
	private String currentPrompt = "$ ";
	private StringBuilder line = new StringBuilder();
	private int line_idx;
	private Listener listener;
	private Monitor monitor;

	/*
	 * DO NOT MODIFY THE SIGNATURE OF THIS CONSTRUCTOR
	 */
	public Shell(ITerminal term, int capacity) {
		m_history = new String[capacity];
		m_term = term;
		this.env = new Environment();
		this.line_idx = 0;
		this.history_index = 0;
		insertStr(currentPrompt);
	}

	public void insertStr(String s) {
		s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			m_term.insert(s.charAt(i));
		}
	}

	@Override
	public void pressed(int keyCode, char keyChar) {
		switch (keyCode) {
		case VirtualKeyCodes.VK_LEFT:
			if (line_idx > 0) {
				m_term.left();
				line_idx--;
			}

			break;
		case VirtualKeyCodes.VK_RIGHT:
			if (line_idx < line.length()) {
				m_term.right();
				line_idx++;
			}

			break;
		case VirtualKeyCodes.VK_DELETE:
			if (line_idx < line.length()) {
				line.deleteCharAt(line_idx);
				m_term.delete();
			}
			break;
		case VirtualKeyCodes.VK_BACK_SPACE:
			if (line_idx > 0) {
				line_idx--;
				line.deleteCharAt(line_idx);
				m_term.backspace();
			}
			break;
		case VirtualKeyCodes.VK_ENTER:
			String command = execCommand(line());
			if (!command.equals("clear")) {
				line.setLength(0);
				line_idx = 0;
				line = new StringBuilder();
				m_term.enter();
				insertStr(prompt());
				;
			} else {
				line.setLength(0);
				line_idx = 0;
				line = new StringBuilder();
				insertStr(prompt());

			}
			pos = -1;// reinit pour parcourir avec up et down le debut de l'historique
			break;
		case VirtualKeyCodes.VK_HOME:
			if (line_idx > 0) {
				for (int i = line_idx; i > 0; i--) {
					m_term.left();
					line_idx--;
				}
			}
			break;
		case VirtualKeyCodes.VK_END:
			if (line_idx < line.length()) {
				for (int i = line_idx; i < line.length(); i++) {
					m_term.right();
					line_idx++;
				}
			}
			break;
		case VirtualKeyCodes.VK_UP:

			if (history_index != 0) {
				if (pos < history_index - 1) {
					pos++;
				}

				while (line_idx > 0) {
					m_term.backspace();
					line_idx--;
				}
				if (pos < history_index) {
					line.setLength(0);
					line.append(m_history[pos]);
					line_idx = line.length();
					insertStr(line());
				} else {
					line.setLength(0);
					line_idx = 0;
				}
			}
			break;

		case VirtualKeyCodes.VK_DOWN:

			if (history_index > 0 && pos > 0) {
				pos--;
			} else {

				break;
			}

			while (line_idx > 0) {
				m_term.backspace();
				line_idx--;
			}

			if (pos < history_index) {
				line.setLength(0);
				line.append(m_history[pos]);
				line_idx = line.length();
				insertStr(line());
			}

			break;
		}

	}

	@Override
	public void released(int keyCode, char keyChar) {
		// pas utilisé
	}

	@Override
	public void typed(char keyChar) {
		line.insert(line_idx, keyChar);
		line_idx++;
		m_term.insert(keyChar);
	}

	@Override
	public String prompt(String prompt) {
		this.currentPrompt = prompt;
		insertStr(prompt);
		return prompt;
	}

	@Override
	public String prompt() {
		return this.currentPrompt;
	}

	public void addHistory(String s) {
		for (int i = history_index; i > 0; i--) {
			m_history[i] = m_history[i - 1];
		}
		m_history[0] = s;
	}

	@Override
	public String[] history() {
		return m_history;
	}

	@Override
	public String line() {
		return line.toString();
	}

	@Override
	public String valueOf(String name) {
		return env.get(name);
	}

	@Override
	public void set(Listener l) {
		this.listener = l;
	}

	@Override
	public void set(IRegistry reg) {
		// Empty implementation is ok for now,
		// this is ignored in task3
	}

	public String execCommand(String line) {
		String words = line();
		String[] origin_list = words.split(" ");
		String[] list = Arrays.copyOfRange(origin_list, 1, origin_list.length);
		String command = origin_list[0];

		switch (command) {
		case "clear":
			addHistory(line());
			history_index++;
			m_term.clear();
			m_term.setCursor(0, 0);
			break;
		case "history":
			addHistory(line());
			history_index++;
			m_history = history();
			for (int i = history_index - 1; i >= 0; i--) {
				m_term.enter();
				insertStr("	   [" + i + "] " + m_history[i]);
			}
			break;
		case "echo":
			addHistory(line());
			history_index++;
			m_term.enter();
			String elem;
			for (int i = 0; i < list.length; i++) {
				if (list[i].charAt(0) == '$') {
					elem = list[i].substring(1).toString();
					insertStr(valueOf(elem) + " ");
				} else {
					insertStr(list[i] + " ");
				}
			}

			break;
		case "env":
			addHistory(line());
			history_index++;
			for (String name : env.listNames()) {
				m_term.enter();
				insertStr(name + "=" + valueOf(name));
			}
			break;
		case "set":
			addHistory(line());
			history_index++;
			if (list.length >= 2) {
				String name = list[0];
				String args = String.join(" ", Arrays.copyOfRange(list, 1, list.length));
				env.put(name, args);

			} else {
				m_term.enter();
				insertStr("Usage : set <name> <args>\n");
			}
			break;
		case "unset":
			addHistory(line());
			history_index++;
			if (origin_list.length >= 2) {
				for (int i = 0; i < list.length; i++) {
					env.del(list[i]);
				}

			} else {
				m_term.enter();
				insertStr("Usage : unset <names>\n");
			}
			break;
		case "":
			break;
		default:
			addHistory(line());
			history_index++;
			m_term.enter();
			insertStr("Command \"" + line() + "\" not found \n");
		}
		return command;
	}

}
