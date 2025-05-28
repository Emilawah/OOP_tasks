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
		

		int cellSize = sizeCell;
		
		int l = (int)(cellSize * 0.6);  // largeur 
		int h = (int)(cellSize * 0.7);  // hauteur 
		
		// Triangle centré autour de (0,0)
		Polygon triangle = new Polygon();
		triangle.addPoint(0, -h / 2);     // sommet (haut, pointe du triangle)
		triangle.addPoint(-l / 2, h / 2); // coin bas gauche
		triangle.addPoint(l / 2, h / 2);  // coin bas droit


		p = m_model.player();
		g.setColor(java.awt.Color.YELLOW);

		int pixelx = (int)p.getX();
		int pixely = (int)p.getY();
		
		paintPlayer(g, p, pixelx, pixely, triangle);
	}






}
