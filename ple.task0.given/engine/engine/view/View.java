package engine.view;

import java.awt.Graphics2D;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.model.Model;
import engine.model.Player;
import oop.graphics.Canvas;
import oop.graphics.Graphics;
import oop.graphics.Graphics.Colors;

public class View {

	private Canvas m_canvas;
	private Model m_model;
	private int mouseX;
	private int mouseY;
	
	public View(Canvas canvas, Model model) {
		m_canvas = canvas;
		m_model = model;
	}

	public void focus(int px, int py) {
		mouseX = px;
		mouseY = py;
	}

	public void paint(Canvas canvas, Graphics2D g) {

		// paint the grid with black lines
		// and grey background.

		// paint the string "Hello", with yellow ink
		// on a blue background, where the left-lower corner
		// of the blue background is at the mouse position

		// then paint the player's entity.
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, width, height);
		
		// nb lignes
		int nrow = m_model.nrows();
		int ncol = m_model.ncols();
		
		int sizeCell = Math.min(width/ncol, height/nrow);

		int gridWidth = sizeCell * ncol;
		int gridHeight = sizeCell * nrow;
		
		int line = Math.max(1, sizeCell/10);
		
		g.setColor(java.awt.Color.BLACK);

		for (int col = 0 ; col <= ncol ; col++) {
			int x = col * sizeCell;
			g.fillRect(0, x, gridWidth, line);
		}
		
		for (int row = 0 ; row <= nrow ; row++) {
			int y = row * sizeCell;
			g.fillRect(y, 0, line, gridHeight);
		}
		
		g.setColor(java.awt.Color.BLUE);
		g.fillRect(mouseX, mouseY-sizeCell, sizeCell, sizeCell);
		
		g.setColor(java.awt.Color.YELLOW);
		g.drawString("Hello",mouseX+5, mouseY-10);
		
		Polygon triangle = new Polygon();
		// Sommet haut du triangle (pointe vers le haut)
		triangle.addPoint(0, -sizeCell / 2);
		// Coin bas gauche
		triangle.addPoint(-sizeCell / 2, sizeCell / 2);
		// Coin bas droit
		triangle.addPoint(sizeCell / 2, sizeCell / 2);

		
		Player p = m_model.player();
		paintPlayer(g,p,p.col()*sizeCell+sizeCell/2,p.row()*sizeCell+sizeCell/2,triangle);
		
	}

	private void paintPlayer(Graphics2D g, Player p, int x, int y, Polygon pg) {
		int d = p.orientation();
		double rot = Math.toRadians(d);
		AffineTransform saved = g.getTransform();
		g.translate(x, y);
		g.rotate(rot);
		g.fillPolygon(pg);
		g.setTransform(saved);
	}

}
