package game.bot.tracker;

import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.model.Entity;
import engine.view.Avatar;
import engine.view.View;

public class TrackerAvatar extends Avatar{

	public TrackerAvatar(View v, Entity e) {
		super(v, e);
	}

	@Override
	public void render(Graphics2D g) {
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		int nrows = model.nrows();
		int ncols = model.ncols();
		int cell = Math.min(w / ncols, h / nrows);
		// peint le joueur
		int l = (int) (cell * 0.6); // largeur
		int hauteur = (int) (cell * 0.7); // hauteur

		// Triangle centré autour de (0,0)
		Polygon triangle = new Polygon();
		triangle.addPoint(0, -hauteur / 2); // sommet (haut, pointe du triangle)
		triangle.addPoint(-l / 2, hauteur / 2); // coin bas gauche
		triangle.addPoint(l / 2, hauteur / 2); // coin bas droit

		// peint le joueur
		
		g.setColor(java.awt.Color.RED);

		int pixelx = (int) ((e.col() / model.getDim()) * v.getSizeCell()+v.getSizeCell()/2);
		int pixely = (int) ((e.row() / model.getDim()) * v.getSizeCell()+v.getSizeCell()/2);

		v.paintPlayer(g, e, pixelx, pixely, triangle);
	}

}
