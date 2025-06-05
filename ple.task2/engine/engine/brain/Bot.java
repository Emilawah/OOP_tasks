package engine.brain;

import engine.IBrain.IBot;

import engine.model.Entity;
import engine.model.Model;

public abstract class Bot implements IBot {
	protected Brain b;
	protected Entity e;
	protected Category category;
	

	protected Bot(Brain b, Entity e) {
		this.b = b;
		this.e = e;
		e.bot = this;
		b.m_listBots.add(this); 
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
		
		turn(Direction.E);
		if (dir.equals(Direction.N)) {
			e.stunt.move(-1, 0);
		} else if (dir.equals(Direction.E)) {
			e.stunt.move(0, 1);
		} else if (dir.equals(Direction.S)) {
			e.stunt.move(1, 0);
		} else if (dir.equals(Direction.W)) {
			e.stunt.move(0, -1);
		}
	}

	protected Entity cell(Direction d) {

		int entity_x = e.row();
		int entity_y = e.col();

		// on converti la direction en absolue (pour savoir ou doit aller l'entité par
		// rapport a la grille)
		Direction absolute = Direction.toAbsolute(e.orientation());;

		if (absolute.equals(Direction.N)) {
			return b.model.entity(entity_x, entity_y-1);
		} else if (absolute.equals(Direction.E)) {
			return b.model.entity(entity_x+1, entity_y);
		} else if (absolute.equals(Direction.S)) {
			return b.model.entity(entity_x, entity_y+1);
		} else if (absolute.equals(Direction.W)) {
			return b.model.entity(entity_x-1, entity_y);
		}
		else {
			throw new IllegalStateException("entités autour");
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
		return null;

	}



}
