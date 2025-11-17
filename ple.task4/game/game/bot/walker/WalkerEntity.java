package game.bot.walker;

import engine.brain.Brain;

import engine.brain.Category;
import engine.model.Entity;
import engine.model.Model;

public class WalkerEntity extends Entity {

	
	public WalkerEntity(Model m, Brain b, int row, int col, int orientation) {
		super(m, row, col, orientation);
		this.category = Category.ADVERSARY;
		new WalkerBot(b, this);
		
	}
	
	

}