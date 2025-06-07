package game.bot.walker;

import engine.brain.Bot;
import engine.brain.Brain;
import engine.brain.Direction;
import engine.model.Entity;

public class WalkerBot extends Bot {

	private int delay;
	private int duration;

	public WalkerBot(Brain b, Entity e) {
		super(b, e);
		duration = 1000;
		delay = duration / 2;
	}

	public void think() {
		if (cell(Direction.F) == null) {
			move(Direction.F);
			return;
		}
		if (cell(Direction.L) == null) {
			turn(Direction.L);
			return;
		}

		if (cell(Direction.R) == null) {
			turn(Direction.R);
			return;
		}

		if (cell(Direction.B) == null) {
			turn(Direction.B);
			return;
		}

	
	}

	@Override
	public Entity entity() {
		return e;
	}

	@Override
	public void think(int elapsed) {
		if (cell(Direction.F) == null) {
			move(Direction.F);
			return;
		}
		if (cell(Direction.L) == null) {
			turn(Direction.L);
			return;
		}

		if (cell(Direction.R) == null) {
			turn(Direction.R);
			return;
		}

		if (cell(Direction.B) == null) {
			turn(Direction.B);
			return;
		}
	}

}