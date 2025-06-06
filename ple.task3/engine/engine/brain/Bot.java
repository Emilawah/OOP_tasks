package engine.brain;

import engine.IBrain.IBot;

import engine.model.Entity;
import engine.model.Model;

public abstract class Bot implements IBot {
	protected Brain b;
	protected Entity e;
	protected Category category;
	protected Model model;
	private boolean wait;
	protected int delay;

	protected Bot(Brain b, Entity e) {
		this.b = b;
		this.e = e;
		e.bot = this;
		b.m_listBots.add(this);
		this.model = e.getModel();
	}

	abstract public void think(int elapsed);

	
	protected void turn(Direction d) {

		if (!d.isRelative()) {
			e.face(d.degrees());
		}
		e.stunt.rotate(d.degrees());

	}

	protected void move(Direction d) {

		Direction dir = d.cardinalOf();

		turn(dir);
		boolean success = false;
		if (dir.equals(Direction.N)) {
			success = e.stunt.move(-1, 0);
			System.out.println("déplacé vers nord");
		} else if (dir.equals(Direction.E)) {
			success = e.stunt.move(0, 1);
		} else if (dir.equals(Direction.S)) {
			success = e.stunt.move(1, 0);
		} else if (dir.equals(Direction.W)) {
			success = e.stunt.move(0, -1);
		}
	}

	protected Entity cell(Direction d) {

		int entity_x = e.row();
		int entity_y = e.col();

		// on converti la direction en absolue (pour savoir ou doit aller l'entité par
		// rapport a la grille)
		Direction absolute = Direction.toAbsolute(e.orientation() + d.degrees()).cardinalOf();
		;

		if (absolute.equals(Direction.N)) {
			return b.model.entity(entity_x-1 ,entity_y);
		} else if (absolute.equals(Direction.E)) {
			return b.model.entity(entity_x , entity_y+1);
		} else if (absolute.equals(Direction.S)) {
			return b.model.entity(entity_x+1, entity_y );
		} else if (absolute.equals(Direction.W)) {
			return b.model.entity(entity_x, entity_y-1);
		} else {
			throw new IllegalStateException("Direction Inconnue");
		}

	}

	protected Entity cell(Direction d, Category c) {
		Entity ent = cell(d);
		if (ent == null) {
			return null;
		}
		if (ent.getCategory().specializes(c)) {
			return ent;
		}
		return null;
	}

	protected Entity closest(Category c) {
		return model.player();

	}

}
