package engine.brain;

class Closest extends ConditionGAL {

	private Category category;

	public Closest(Category c) {
		this.category = c;
	}

	@Override
	public boolean eval(Bot bot) {
		return bot.closest(category) != null;
	}

}
