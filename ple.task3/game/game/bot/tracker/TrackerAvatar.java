package game.bot.tracker;

import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.model.Entity;
import engine.view.Avatar;
import engine.view.View;
import game.bot.walker.StuntWalker;

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

		int col = e.col();
		int row = e.row();
		int pixelX = (int) ((col + 0.5) * cell);
		int pixelY = (int) ((row + 0.5) * cell);
		
		TrackerStunt sw = (TrackerStunt) e.stunt;
		java.awt.Color playerColor = java.awt.Color.RED;

		if (sw.progress() > 0) {
			double progress = sw.progress() / 100.0;

			// Direction du mouvement
			int dx = sw.getSTX();
			int dy = sw.getSTY();

			// Si on est dans la première moitié de la progression
			if (sw.progress() < 50) {

				int targetCol = col + dx;
				int targetRow = row + dy;

				// Vérification s'il y a une entité en face
				Entity target = model.entity(targetRow, targetCol);

				if (target != null) {
					playerColor = java.awt.Color.MAGENTA;
					pixelX = (int) ((col + 0.5 + dx * progress) * cell);
					pixelY = (int) ((row + 0.5 + dy * progress) * cell);
				} else {
					playerColor = java.awt.Color.RED;
					pixelX = (int) ((col + 0.5 + dx * progress*2) * cell);
					pixelY = (int) ((row + 0.5 + dy * progress*2) * cell);

				}

			} else {
				g.setColor(java.awt.Color.RED);
			}
		}
		v.paintPlayer(g, e, pixelX, pixelY, triangle);
	}
}
