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
	protected int sizeCell;
	protected Player p;

	protected float zoom = 1;
	protected int graphX = 0;
	protected int graphY = 0;

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

		m_model.setView(this);

		int width = canvas.getWidth();
		int height = canvas.getHeight();
		int nrow = m_model.nrows();
		int ncol = m_model.ncols();
		
		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		AffineTransform saved = g.getTransform();
		g.setTransform(new AffineTransform());
		
		sizeCell = Math.min(width / ncol, height / nrow);

		drawGrid(canvas, g);

		g.setTransform(saved);
	}

	public void zoomOut() {
		if (zoom > 1)
			zoom -= 0.2;

	}

	public void zoomIn() {
		if (zoom < 5)
			zoom += 0.2;

	}

	public void resetZoom() {
		zoom = 1;
		graphX = 0;
		graphY = 0;
	}

	public void translate(int x, int y) {
		graphX = graphX + x;
		graphY = graphY + y;
	}

	public float getSizeCell() {
		return sizeCell;
	}

	protected void drawGrid(Canvas canvas, Graphics2D g) {

		g.setColor(java.awt.Color.BLACK);

		float cellZoomed = sizeCell * zoom;
		
		for (int col = 0; col <= m_model.ncols(); col++) {
			int x = (int) (col * cellZoomed+graphX);
			g.drawLine(x, 0, x, canvas.getHeight());

		}

		for (int row = 0; row <= m_model.nrows(); row++) {
			int y = (int) (row * cellZoomed+graphY);
			g.drawLine(0, y, canvas.getWidth(), y);
		}
		
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
