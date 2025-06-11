package engine.brain;

import engine.IBrain.IBot;

import engine.model.Entity;
import engine.model.Model;
import engine.model.Player;

public abstract class Bot implements IBot {

	protected Mode mode;
	protected Brain b;
	protected Entity e;
	protected Category category;
	protected Model model;

	protected Bot(Brain b, Entity e) {
		this.b = b;
		this.e = e;
		e.bot = this;
		b.m_listBots.add(this);
		this.model = e.getModel();
	}

	abstract public void think(int elapsed);

	protected void turn(Direction d) {
		if (d.isRelative()) {
			Direction abs = Direction.cardinalOrient(e.orientation(), d);
			e.face(abs.degrees());
		} else {
			e.face(d.degrees());
		}
	}


	protected void move(Direction d) {

		Direction dir = Direction.cardinalOrient(e.orientation(), d);

		
		turn(dir);
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

		int row = e.row();
		int col = e.col();

		Direction cardinal = Direction.cardinalOrient(e.orientation(), d);

		if (cardinal.equals(Direction.N)) {
			row--;
		} else if (cardinal.equals(Direction.E)) {
			col++;
		} else if (cardinal.equals(Direction.S)) {
			row++;
		} else if (cardinal.equals(Direction.W)) {
			col--;
		}
		return b.model.entity(row, col);
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