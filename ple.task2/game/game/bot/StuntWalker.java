package game.bot;

import engine.model.Entity;
import engine.model.Model;
import engine.model.Stunt;

public class StuntWalker extends Stunt{

	public StuntWalker(Model m, Entity e) {
		super(m, e);
		
	}
	
	@Override
	public void move(int nrows, int ncols) {
		e.bot.think();
	}

}
