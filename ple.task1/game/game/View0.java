package game;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.IModel;
import engine.model.Model;
import engine.model.Player;
import engine.view.View;
import oop.graphics.Canvas;

public class View0 extends View {


	public View0(Canvas canvas, IModel model) {
		super(canvas, model);
	}

	public void focus(int px, int py) {
		super.mouseX = px;
		super.mouseY = py;
	}
	
	public void paint(Canvas canvas, Graphics2D g) {
		
		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// pour gérer le zoom
		super.paint(g, gx,gy, zoom);
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, width, height);

		// nb lignes
		int nrow = m_model.nrows();
		int ncol = m_model.ncols();

		int sizeCell = Math.min(width / ncol, height / nrow);
		
		debug(canvas, g);
		
		Polygon triangle = new Polygon();

		triangle.addPoint(0, -sizeCell / 2);
		triangle.addPoint(-sizeCell / 2, sizeCell / 2);
		triangle.addPoint(sizeCell / 2, sizeCell / 2);

		p = m_model.player();
		g.setColor(java.awt.Color.YELLOW);

		paintPlayer(g, p, p.col() * sizeCell + sizeCell / 2, p.row() * sizeCell + sizeCell / 2, triangle);
	}






}
