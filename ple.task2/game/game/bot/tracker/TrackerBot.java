package game.bot.tracker;

import engine.brain.Bot;
import engine.brain.Brain;
import engine.brain.Category;
import engine.brain.Direction;
import engine.model.Entity;
import engine.utils.Utils;

public class TrackerBot extends Bot {

	protected TrackerBot(Brain b, Entity e) {
		super(b, e);
	}

	@Override
	public Entity entity() {
		return e;
	}

	@Override
	public void think() {
		move(dirOf(closest(Category.Player)).cardinalOf());
	}

	@Override
	public void think(int elapsed) {
		Entity e = closest(Category.Adversary);
		if (e == null)
			return;
		Direction d = dirOf(e).cardinalOf();
		if (d == Direction.N) {
			move(Direction.N);
			return;
		}
		if (d == Direction.E) {
			move(Direction.E);
			return;
		}
		if (d == Direction.S) {
			move(Direction.S);
			return;
		}
		if (d == Direction.W) {
			move(Direction.W);
			return;
		}
	}

	private Direction dirOf(Entity target) {
		
		float dx =  e.row() - target.row();
		float dy =  e.col() - target.col();
		int coord = Utils.theta(dx, -dy);

		return Direction.toAbsolute(coord);
	}

}
