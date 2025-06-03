package engine.brain;

import engine.IBrain.IBot;
import engine.model.Entity;
import engine.model.Model;

public abstract class Bot implements IBot {
	protected Brain b;
	protected Entity e;

	protected Bot(Brain b, Entity e) {
		this.b = b;
		this.e = e;
		e.bot = this;
		
	}

	abstract public void think(int elapsed);

	protected void turn(Direction d) {
		int angle;
		if (d.isRelative()) {
			angle = d.degrees();
		} else {
			angle = d.degrees() - e.orientation();
		}
		e.stunt.rotate(angle);
	}

	protected void move(Direction d) {
		// move l'entité d'une case dans la grille
		int entity_x = e.row();
		int entity_y = e.col();

		// on converti la direction en absolue (pour savoir ou doit aller l'entité par rapport a la grille)
		Direction absolute = toAbsolute(d);
		
		if (absolute.equals(Direction.N)) {
			entity_y--;
		} else if (absolute.equals(Direction.E)) {
			entity_x++;
		} else if (absolute.equals(Direction.S)) {
			entity_y++;
		} else if (absolute.equals(Direction.W)) {
			entity_x--;
		}
		e.stunt.move(entity_x, entity_y);
	}

	protected Entity cell(Direction d) {
		Model model = e.getModel();

		int entity_x = e.row();
		int entity_y = e.col();

		// on converti la direction en absolue (pour savoir ou doit aller l'entité par rapport a la grille)
		Direction absolute = toAbsolute(d);
		
		if (absolute.equals(Direction.N)) {
			entity_y--;
		} else if (absolute.equals(Direction.E)) {
			entity_x++;
		} else if (absolute.equals(Direction.S)) {
			entity_y++;
		} else if (absolute.equals(Direction.W)) {
			entity_x--;
		}

		return model.entity(entity_x, entity_y);
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
		return null;
		
	}

	protected Direction toAbsolute(Direction d) {
		if (d.isRelative()) {
			int angle = e.orientation() + d.degrees();
			return Direction.N.rotate(angle).cardinalOf();
		} else {
			return d;
		}
	}

}
