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
import game.bot.tracker.TrackerAvatar;
import game.bot.tracker.TrackerEntity;
import game.bot.tracker.TrackerStunt;
import game.bot.walker.*;
import game.player.AvatarPlayer;
import game.player.StuntPlayer;
import oop.graphics.Canvas;

public class View0 extends View {

	public View0(Canvas canvas, IModel model) {
		super(canvas, model);

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

		// on affiche chaque avatar dans la vue
		for (Avatar a : m_avatarList) {
			a.render(g);
		}

	}

	@Override
	public void birth(Entity e) {
		if (e instanceof Player) {
			new AvatarPlayer(this, e);
			new StuntPlayer((Model) m_model, e);
		} else if (e instanceof WalkerEntity) {
			new AvatarWalker(this, e);
			new StuntWalker((Model) m_model, e);
		}else if (e instanceof TrackerEntity) {
			new TrackerAvatar(this, e);
			new TrackerStunt((Model) m_model, e);
		}
		m_avatarList.add((Avatar) e.avatar); // ajout de l'avatar à la liste
	}

	@Override
	public void death(Entity e) {
		if (e.avatar != null) {
			e.avatar = null;
		}
	}

}
