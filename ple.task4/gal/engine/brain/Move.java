package engine.brain;

class Move extends ActionGAL {
	// The optional parameters of a Move
	Direction d; // its direction
	int i; // a number of step

	// plusieurs constructeurs

	Move() {
		// par defaut
		d = Direction.F;
		i = 1;
	}

	Move(Direction d, int i) {
		this.d = d;
		this.i = i;
	}

	// REQUIRED

	void exec(Bot b) {
		b.move(d);
	}

	@Override
	boolean acceptedBy(Bot b) {

		return !b.entity().stunt.moved();
	}
}
