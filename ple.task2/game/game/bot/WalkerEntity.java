package game.bot;

import engine.brain.Category;
import engine.model.Entity;
import engine.model.Model;

public class WalkerEntity extends Entity {

	public WalkerEntity(Model m, int row, int col, int orientation) {
		super(m, row, col, orientation);
		this.category = Category.Adversary;
	}
}