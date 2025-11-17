package game.bot.tracker;

import engine.brain.Brain;
import engine.brain.Category;
import engine.model.Entity;
import engine.model.Model;

public class TrackerEntity extends Entity{

	public TrackerEntity(Model m,Brain b, int r, int c, int o) {
		super(m, r, c, o);
		this.category = Category.ADVERSARY;
		new TrackerBot(b, this);
	}

}
