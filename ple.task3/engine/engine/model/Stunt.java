package engine.model;

public abstract class Stunt {
	protected Model m;
	protected Entity e;

	protected Stunt(Model m, Entity e) {
		this.e = e;
		this.m = m;
		e.stunt = this;
	}

	// ACTIONS
	public void move(int nrows, int ncols) {
		m.move(e, nrows, ncols);
	}

	public void rotate(int angle) {
		e.face(angle);
	}

}