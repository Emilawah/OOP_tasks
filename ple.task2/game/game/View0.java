package game;

import java.awt.Graphics2D;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.IModel;
import engine.model.Entity;
import engine.model.Model;
import engine.model.Player;
import engine.view.View;
import game.player.AvatarPlayer;
import game.player.StuntPlayer;
import oop.graphics.Canvas;

public class View0 extends View {

	public View0(Canvas canvas, IModel model) {
		super(canvas, model);
		birth(m_model.player());
	}

	public void focus(int px, int py) {
		super.mouseX = px;
		super.mouseY = py;
	}

	public void paint(Canvas canvas, Graphics2D g) {

		// Réafficher le fond en gris (pour déplacer la grille arrière plan propre)

		g.setColor(java.awt.Color.GRAY);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		super.paint(g, graphX, graphY, zoom);

		// peint la grille
		debug(canvas, g);

		// peint le(s) entité(s)
		drawEntitie(g);
		// peint le joueur
		((AvatarPlayer) m_model.player().avatar).render(g);
		// drawPlayer(g);

	}

	private void drawEntitie(Graphics2D g) {

		int w = m_canvas.getWidth();
		int h = m_canvas.getHeight();
		int nrows = m_model.nrows();
		int ncols = m_model.ncols();
		int cell = Math.min(w / ncols, h / nrows);

		g.setColor(java.awt.Color.RED);
		int pointSize = 10;

		for (int row = 0; row < nrows; row++) {
			for (int col = 0; col < ncols; col++) {
				if (m_model.entity(row, col) != null) {
					int x = col * cell + cell / 2 - pointSize / 2;
					int y = row * cell + cell / 2 - pointSize / 2;
					g.fillOval(x, y, pointSize, pointSize);
				}
			}
		}
	}

	private void drawPlayer(Graphics2D g) {
		// peint le joueur
		int l = (int) (sizeCell * 0.6); // largeur
		int h = (int) (sizeCell * 0.7); // hauteur

		// Triangle centré autour de (0,0)
		Polygon triangle = new Polygon();
		triangle.addPoint(0, -h / 2); // sommet (haut, pointe du triangle)
		triangle.addPoint(-l / 2, h / 2); // coin bas gauche
		triangle.addPoint(l / 2, h / 2); // coin bas droit

		// peint le joueur
		p = m_model.player();
		g.setColor(java.awt.Color.YELLOW);

		int pixelx = (int) ((p.getX() / m_model.getDim()) * getSizeCell());
		int pixely = (int) ((p.getY() / m_model.getDim()) * getSizeCell());

		paintPlayer(g, p, pixelx, pixely, triangle);
	}

	@Override
	public void birth(Entity e) {
		if (e instanceof Player) {
			new AvatarPlayer(this, e);
			new StuntPlayer((Model) m_model,e);
		}
	}

	@Override
	public void death(Entity e) {
		if (e.avatar != null) {
			e.avatar = null;
		}
	}

}
