package game.bot;

import engine.brain.Brain;
import engine.brain.Category;
import engine.model.Entity;
import engine.model.Model;

public class WalkerEntity extends Entity {

	protected WalkerBot walkerbot;
	
	public WalkerEntity(Model m, int row, int col, int orientation) {
		super(m, row, col, orientation);
		this.category = Category.Adversary;
		Brain brain = new Brain();
		walkerbot = new WalkerBot(brain, this);
		
	}
	
	public WalkerBot getBot() {
		return walkerbot;
	}
}