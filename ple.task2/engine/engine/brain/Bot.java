package engine.brain;

import engine.IBrain.IBot;
import engine.model.Entity;


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
		if(d.isRelative()) {
			angle = d.degrees();
		}else {
			angle = d.degrees() - e.orientation();
		}
		e.stunt.rotate(angle);
	}

	protected void move(Direction d) {
		
	}

	protected Entity cell(Direction d);

	protected Entity cell(Direction d, Category c);

	protected Entity closest(Category c) {
		
	}
}
