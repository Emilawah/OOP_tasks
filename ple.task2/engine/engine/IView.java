package engine;

import java.awt.Graphics2D;

import engine.model.Entity;
import oop.graphics.Canvas;

public interface IView {
	void focus(int px, int py);

	public void paint(Canvas canvas, Graphics2D g);

	public void paint(Graphics2D g, int x, int y, float zoom);

	public void zoomIn();

	public void zoomOut();

	public void resetZoom();

	public void translate(int x, int y);

	public float getSizeCell();

	public float getZoom();

	public float getGraphX();

	public float getGraphY();
	
	public void birth(Entity e);	

	public void death(Entity e);
}
