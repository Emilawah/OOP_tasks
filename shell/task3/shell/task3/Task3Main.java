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
package shell.task3;

import java.awt.Dimension;


import oop.graphics.Canvas;
import oop.runtime.EventPump;
import oop.shell.IShell;
import oop.shell.ITerminal;
import oop.tasks.Task;
import oop.term.Terminal;
import oop.utils.Shell;

public class Task3Main implements Runnable {

	public static void main(String args[]) {
		EventPump ep = new EventPump();
		Dimension d = new Dimension(640, 480);
		Task3Main boot = new Task3Main();
		ep.boot(d, boot);
	}

	Canvas m_canvas;
	Terminal m_term;
	Shell m_shell;

	public void run() {
		Task task = Task.task();
		m_canvas = (Canvas) task.find("canvas");
		m_term = new Terminal(m_canvas, "Ubuntu Mono", 35);

		new PaintListener(m_canvas, m_term);
		
		task.post(() -> {
				m_shell = new Shell(m_term,100);
				new KeyListener(m_canvas, m_shell);
		},100);
	}

}
