package engine.brain;

class Cell extends ConditionGAL {
	Direction dir;
	int radius;
	Category cat;

	// plusieurs constructeurs

	Cell(Direction d, Category c) {
		this.dir = d;
		this.cat = c;
	}

	Cell(Direction d, Category c, int r) {
		this.dir = d;
		this.cat = c;
		this.radius = r;
	}

	// REQUIRED
	boolean eval(Bot bot){
        return bot.cell(dir, radius, cat) != null;
    }

}
