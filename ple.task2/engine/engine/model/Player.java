package engine.model;

public class Player extends Entity {
	protected float px;
	protected float py;
	
	public Player(Model m_model, int x, int y, int o) {
		super(m_model, x, y, o);
		px = m_model.getDim() * x;
		py = m_model.getDim() * y;
	}

	public float getX() {
		return px;
	}
	
	public float getY() {
		return py;
	}
	
	
	
	

	
	


	

	
}
