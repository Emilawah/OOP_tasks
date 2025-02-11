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
package shell.task1;

import oop.graphics.Canvas;

import oop.graphics.Graphics;
import oop.graphics.Font;
import oop.graphics.Graphics.Colors;
import oop.streams.VirtualKeyCodes;
import oop.tasks.Task;

public class TextLine {

	/*
	 * Listener to the evolution of this line of text. The methods must be invoked
	 * on the task that set the listener.
	 */

	private Listener listener;
	private StringBuilder line = new StringBuilder();
	private int mouseX, mouseY;
	private int cursor_position;
	private boolean cursorVisible = true;
	private Font font;

	public interface Listener {
		/*
		 * Invoked when the given character is inserted at the given position in the
		 * current line of text. The valid position are within the range [0:length] with
		 * length being the current length of the line of text.
		 */
		void inserted(int pos, char c);

		/*
		 * Invoked when the given character is inserted at the given position in the
		 * current line of text. The valid position are within the range [0:length[ with
		 * length being the current length of the line of text.
		 */
		void deleted(int pos, char c);

		/*
		 * Invoked when the line of text is validated, which happens when the character
		 * '\n' has been typed. The line must not include that character '\n'.
		 */
		void validated(String line);
	}

	public void set(Listener l) {
		this.listener = l;
	}

	public TextLine(Canvas canvas) {

		canvas.set(new PaintListener());
		canvas.set(new MouseListener());
		canvas.set(new KeyListener());
		this.line = new StringBuilder();
		cursor_position = 0;
		this.set(listener);
		canvas.repaint();
	}

	class KeyListener implements Canvas.KeyListener {

		public KeyListener() {

		}

		@Override
		public void pressed(Canvas canvas, int keyCode, char keyChar) {

			switch (keyCode) {
			case VirtualKeyCodes.VK_ENTER:

				line.setLength(0);
				cursor_position = 0;
				if(listener != null) {
					listener.validated(line.toString());
				}
				canvas.repaint();
				break;
			case VirtualKeyCodes.VK_BACK_SPACE:
				if (line.length() != 0 && cursor_position >0) {
					line.deleteCharAt(cursor_position-1);
					cursor_position--;
					canvas.repaint();
				}
				break;
			case VirtualKeyCodes.VK_DELETE:
				if (cursor_position < line.length()) {
					line.deleteCharAt(cursor_position);
					canvas.repaint();
				}
				break;
			case VirtualKeyCodes.VK_LEFT:
				if (cursor_position > 0) {
					cursor_position--;
					canvas.repaint();
				}
				break;
			case VirtualKeyCodes.VK_RIGHT:
				if (cursor_position < line.length()) {
					cursor_position++;
					canvas.repaint();
				}
				break;

			default:
				break;
			}
		}

		@Override
		public void released(Canvas canvas, int keyCode, char keyChar) {
		}

		@Override
		public void typed(Canvas canvas, char keyChar) {
			line.insert(cursor_position, keyChar);
			cursor_position++;
			if(listener != null) {
				listener.inserted(cursor_position, keyChar);
			}
			canvas.repaint();
		}

	}

	class MouseListener implements Canvas.MouseListener {

		@Override
		public void moved(Canvas canvas, int x, int y) {
			mouseX = x;
			mouseY = y - 30;
			canvas.repaint();
		}

		@Override
		public void pressed(Canvas canvas, int bno, int x, int y) {
			// pas utilisé dans la tache1
		}

		@Override
		public void released(Canvas canvas, int bno, int x, int y) {
			// pas utilisé dans la tache1
		}
	}

	class PaintListener implements Canvas.PaintListener {

		@Override
		public void paint(Canvas canvas, Graphics g) {
			g.setColor(Colors.black);
			g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			g.setColor(Colors.white);
			font = g.getFont("Arial", Font.PLAIN, 30);
			g.setFont(font);
			
			int cursor = mouseX + g.getFont().getWidth(line.substring(0, cursor_position));
			if (cursorVisible) {
				g.fillRect(cursor, mouseY, 5, 30);
			}
			g.drawString(line.toString(), mouseX, mouseY+30);

		}

		@Override
		public void visible(Canvas canvas) {
			Task task = Task.task();
			task.post(new Runnable() {
				public void run() {
					cursorVisible = !cursorVisible;
					canvas.repaint();
					task.post(this, 500);
				}
			});
		}

		@Override
		public void revoked(Canvas canvas) {
		}
	}
}
