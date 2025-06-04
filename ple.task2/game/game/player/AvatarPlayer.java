package game.player;

import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.model.Entity;
import engine.model.Player;
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
		// peint le joueur
		int l = (int) (cell * 0.6); // largeur
		int hauteur = (int) (cell * 0.7); // hauteur

		// Triangle centré autour de (0,0)
		Polygon triangle = new Polygon();
		triangle.addPoint(0, -hauteur / 2); // sommet (haut, pointe du triangle)
		triangle.addPoint(-l / 2, hauteur / 2); // coin bas gauche
		triangle.addPoint(l / 2, hauteur / 2); // coin bas droit

		// peint le joueur
		Player p = model.player();
		g.setColor(java.awt.Color.YELLOW);

		int pixelx = (int) ((p.getX() / model.getDim()) * v.getSizeCell());
		int pixely = (int) ((p.getY() / model.getDim()) * v.getSizeCell());

		v.paintPlayer(g, p, pixelx, pixely, triangle);
	}

}
