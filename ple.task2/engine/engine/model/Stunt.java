package engine.model;

public abstract class Stunt {
	protected Model m;
	protected Entity e;

	protected Stunt(Model m, Entity e) {
		this.e = e;
		this.m = m;
		e.stunt = this;
	}

	//ACTIONS 
	abstract public void move(int nrows, int ncols);

	abstract public void rotate(int angle);
}
