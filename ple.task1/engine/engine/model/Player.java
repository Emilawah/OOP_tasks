package engine.model;

public class Player extends Entity {
	protected float px;
	protected float py;
	protected float speed_meter = 1; // 1 déplacement -> vaut le nombre de mètres parcouru
	private boolean isMoving;
	
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
	
	/*
	 * Move this entity up one row.
	 */
	public void up() {
		move(0, -speed_meter);
	}

	/*
	 * Move this entity down one row.
	 */
	public void down() {
		move(0, speed_meter);
	}

	/*
	 * Move this entity left one column.
	 */
	public void left() {
		move(-speed_meter, 0);
	}

	/*
	 * Move this entity right one column.
	 */
	public void right() {
		move(speed_meter, 0);
	}

	public void startMove() {
		isMoving = true;
	}
	
	public void stopMove() {
		isMoving = false;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	public void movePlayer() {
		if(isMoving) {
			float dx=0,dy=0;
			switch(m_orientation) {
			case 0:
				dy = -speed_meter;
				break;
			case 90:
				dx = speed_meter;
				break;
			case 180:
				dy = speed_meter;
				break;
			case 270:
				dx = -speed_meter;
				break;
			case 360:
				dy = -speed_meter;
				break;

			}
			m_model.moveM(dx,dy);
		}
	}
	
	public void move(float x , float y) {
		m_model.moveM(x, y);
	}
}
