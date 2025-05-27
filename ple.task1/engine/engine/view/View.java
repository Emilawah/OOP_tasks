package engine.view;

import java.awt.Graphics2D;

import engine.IModel;
import engine.IView;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.model.Model;
import engine.model.Player;
import oop.graphics.Canvas;
import oop.graphics.Graphics;
import oop.graphics.Graphics.Colors;

public abstract class View implements IView {

	protected Canvas m_canvas;
	protected IModel m_model;
	protected int mouseX;
	protected int mouseY;
	protected Player p;

	protected int zoom=1;
	protected int gx=1;
	protected int gy=1;
	
	
	
	
	protected View(Canvas canvas, IModel model) {
		m_canvas = canvas;
		m_model = model;
		p = m_model.player();
	}

	public abstract void focus(int px, int py);

	public abstract void paint(Canvas canvas, Graphics2D g);

	public void paint(Graphics2D g, int x, int y, float zoom) {
		g.translate(x, y);
		g.scale(zoom, zoom);
	}

	public void debug(Canvas canvas, Graphics2D g) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, width, height);

		// nb lignes
		int nrow = m_model.nrows();
		int ncol = m_model.ncols();

		int sizeCell = Math.min(width / ncol, height / nrow);

		int gridWidth = sizeCell * ncol;
		int gridHeight = sizeCell * nrow;

		int line = Math.max(1, sizeCell / 10);

		g.setColor(java.awt.Color.BLACK);

		for (int col = 0; col <= ncol; col++) {
			int x = col * sizeCell;
			g.fillRect(0, x, gridWidth+line, line);
		}

		for (int row = 0; row <= nrow; row++) {
			int y = row * sizeCell;
			g.fillRect(y, 0, line, gridHeight+line);
		}
	}

	public void zoomOut() {
		if(zoom>1)
		zoom--;
		
	}
	public void zoomIn() {
		if(zoom<5)
		zoom++;
		
	}
	public void resetZoom() {
		zoom=1;
		gx = 0;
		gy = 0;
	}
	public void translate(int x,int y) {
		gx=gx+x;
		gy=gy+y;
	}
	
	protected void paintPlayer(Graphics2D g, Player p, int x, int y, Polygon pg) {
		int d = p.orientation();
		double rot = Math.toRadians(d);
		AffineTransform saved = g.getTransform();
		g.translate(x, y);
		g.rotate(rot);
		g.fillPolygon(pg);
		g.setTransform(saved);
	}

}
