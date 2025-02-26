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
package oop.term;

import java.awt.Color;


import oop.graphics.Canvas;

import oop.graphics.Graphics;
import oop.graphics.Graphics.Colors;
import oop.shell.ITerminal;
import oop.graphics.Font;

public class Terminal implements ITerminal {

	private Canvas canvas;
	private Cursor cursor;
	private Text text;
	private String fontname;
	private int fontsize;
	private Graphics g;
	private Font font;
	private Monitor monitor;
	private Listener listener;
	private boolean visible = true;

	public Terminal(Canvas canvas, String fontName, int fontSize) {
		this.canvas = canvas;
		this.fontname = fontName;
		this.fontsize = fontSize;
		
		this.cursor = new Cursor(0,0);
		this.text = new Text(0,0);
		
	}

	/*
	 * Sets the cursor at the given coordinates, coordinates given in pixels on the
	 * canvas. To translate (x,y) in (row,column), one needs to use the font used to
	 * display the characters, because each characters has its own width, for a
	 * given font.
	 */
	
	public void clicked(int x, int y) {
		int col = x;
		int row = y-15;
		setCursor(row,col);
	}

	/*
	 * Request this terminal to repaint itself on the given canvas with the given
	 * graphics.
	 */
	public void paint(Canvas canvas, Graphics g) {

		g.setColor(Colors.black);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		font = g.getFont(fontname,font.PLAIN,fontsize);
		g.setFont(font);
		cursor.setCursor(row(),column());

		g.setColor(Colors.green);
		
		g.fillRect(column(), row(), g.getFont().getWidth('W'), 30);
			
	}

	@Override
	public int ncols() {
		return canvas.getWidth() / font.getHeight();
	}

	@Override
	public int nrows() {
		return canvas.getHeight() / font.getWidth('W');
	}

	@Override
	public void setCursor(int row, int col) {
		cursor.setCursor(row,col);
		System.out.println("Ncols : "+ncols());
		System.out.println("Nrows : "+nrows());
		System.out.println("Column : "+column());
		System.out.println("Row : "+row());
		canvas.repaint();
	}

	@Override
	public int column() {
		return cursor.getCol();
	}

	@Override
	public int row() {
		return cursor.getRow();
	}

	@Override
	public void left() {
		cursor.left();
		canvas.repaint();
	}

	@Override
	public void right() {
		cursor.right();
		canvas.repaint();
	}

	@Override
	public void up() {
		cursor.up();
		canvas.repaint();
	}

	@Override
	public void down() {
		cursor.down();
		canvas.repaint();
	}

	@Override
	public void delete() {
		text.delete();
	}

	@Override
	public void backspace() {
		text.backspace();
	}

	@Override
	public void clear() {
		text.clear();
	}

	@Override
	public void clear(int row) {
		text.clearRow(row);
	}

	@Override
	public void enter() {
		text.enter();
	}

	@Override
	public void insert(char c) {
		text.insert(c);
	}

	@Override
	public void set(Listener l) {
		this.listener = l;
	}

	@Override
	public void monitor(Monitor l) {
		this.monitor = l;
		
	}

}




  