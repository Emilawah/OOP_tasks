package game.player;

import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.model.Entity;
import engine.model.Player;
import engine.model.Stunt;
import engine.view.Avatar;
import engine.view.View;

public class AvatarPlayer extends Avatar {

	public AvatarPlayer(View v, Entity e) {
		super(v, e);

	}

	@Override
	public void render(Graphics2D g) {
	    int w = canvas.getWidth();
	    int h = canvas.getHeight();
	    int nrows = model.nrows();
	    int ncols = model.ncols();
	    int cell = Math.min(w / ncols, h / nrows);

	    int l = (int) (cell * 0.6);
	    int hauteur = (int) (cell * 0.7);

	    Polygon triangle = new Polygon();
	    triangle.addPoint(0, -hauteur / 2);
	    triangle.addPoint(-l / 2, hauteur / 2);
	    triangle.addPoint(l / 2, hauteur / 2);

	    Player p = model.player();
	    g.setColor(java.awt.Color.YELLOW);

	    // Coordonnées en cases
	    int col = p.col();
	    int row = p.row();

	    // Position pixel du centre de la case
	    int pixelX = (int)((col + 0.5) * cell);
	    int pixelY = (int)((row + 0.5) * cell);

	    v.paintPlayer(g, p, pixelX, pixelY, triangle);
	}






}
