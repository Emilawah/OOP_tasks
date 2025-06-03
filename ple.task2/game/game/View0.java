package game;

import java.awt.Graphics2D;


import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.IModel;
import engine.brain.Category;
import engine.model.Entity;
import engine.model.Model;
import engine.model.Player;
import engine.view.Avatar;
import engine.view.View;
import game.player.AvatarPlayer;
import game.player.StuntPlayer;
import game.bot.*;
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
		// shape of entity
		drawEntity(g);

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
	
	private void drawEntity(Graphics2D g) {
        int nrows = m_model.nrows();
        int ncols = m_model.ncols();
        
        
        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                Entity e = m_model.entity(row, col);
                if (e != null && e.avatar instanceof Avatar) {
                    ((Avatar) e.avatar).render(g);
                }
            }
        }
    }
	
	@Override
	public void birth(Entity e) {
		if (e instanceof Player) {
			new AvatarPlayer(this, e);
			new StuntPlayer((Model) m_model,e);
		}
		else if(e instanceof WalkerEntity) {
			new AvatarWalker(this, e);
			new StuntWalker((Model) m_model, e);
		
		 
		}
	}

	@Override
	public void death(Entity e) {
		if (e.avatar != null) {
			e.avatar = null;
		}
	}

}
