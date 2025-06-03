package game.bot;

import engine.brain.Bot;
import engine.brain.Brain;
import engine.brain.Direction;
import engine.model.Entity;

public class WalkerBot extends Bot {
	public WalkerBot(Brain b, Entity e) {
		super(b, e);
		
	}

	public void think(int elapsed) {
		if (cell(Direction.F) == null)
			move(Direction.F);
		if (cell(Direction.L) == null)
			turn(Direction.L);
		if (cell(Direction.R) == null)
			turn(Direction.R);
		if (cell(Direction.B) == null)
			turn(Direction.B);
	}

	@Override
	public Entity entity() {
		return e;
	}

	@Override
	public void think() {
		think(16);
	}
}