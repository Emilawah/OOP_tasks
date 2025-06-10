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

		int col = p.col();
		int row = p.row();
		int pixelX = (int) ((col + 0.5) * cell);
		int pixelY = (int) ((row + 0.5) * cell);
		int anglePlayer = p.orientation();

		StuntPlayer sp = (StuntPlayer) p.stunt;
		java.awt.Color playerColor = java.awt.Color.YELLOW;

		if (sp.progress() > 0 && sp.action() != null) {
			int kind = sp.action().kind();
			if (kind == 0) {

				double progress = sp.progress() / 100.0;

				// Direction du mouvement
				int dx = sp.getSPX();
				int dy = sp.getSPY();

				// Si on est dans la première moitié de la progression
				if (sp.progress() < 50) {

					int targetCol = col + dx;
					int targetRow = row + dy;

					// Vérification s'il y a une entité en face
					Entity target = model.entity(targetRow, targetCol);

					if (target != null) {
						playerColor = java.awt.Color.MAGENTA;
						pixelX = (int) ((col + 0.5 + dx * progress) * cell);
						pixelY = (int) ((row + 0.5 + dy * progress) * cell);
					} else {
						playerColor = java.awt.Color.YELLOW;
						pixelX = (int) ((col + 0.5 + dx * progress * 2) * cell);
						pixelY = (int) ((row + 0.5 + dy * progress * 2) * cell);

					}
				}

			} else if (kind == 1) {
				if (sp.progress() < 100) {
					int targetAngle = sp.getAngle();
					double progressR = sp.progress() / 100.0;

					int delta = (targetAngle - anglePlayer + 360) % 360;
					if (delta > 180) {
						delta -= 360;
					}
					double newAngle = (anglePlayer + delta * progressR + 360) % 360;

					p.face((int) newAngle);
				}
			}

		}
		g.setColor(playerColor);
		v.paintPlayer(g, p, pixelX, pixelY, triangle);
	}

}
