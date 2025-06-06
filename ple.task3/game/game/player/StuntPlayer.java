package game.player;

import engine.model.Entity;
import engine.model.Model;
import engine.model.Stunt;

public class StuntPlayer extends Stunt {

	protected float speed_meter = 1; // 1 déplacement -> vaut le nombre de mètres parcouru

	public StuntPlayer(Model m, Entity e) {
		super(m, e);

	}

	public void up() {
		e.face(0);
		m.moveM(0, -speed_meter);
	}

	/*
	 * Move this entity down one row.
	 */
	public void down() {
		e.face(180);
		m.moveM(0, speed_meter);
	}

	/*
	 * Move this entity left one column.
	 */
	public void left() {
		e.face(270);
		m.moveM(-speed_meter, 0);
	}

	/*
	 * Move this entity right one column.
	 */
	public void right() {
		e.face(90);
		m.moveM(speed_meter, 0);
	}

	@Override
	public void rotate(int angle) {
		if (angle < 0) {
			angle = cardinalOf_1(e.orientation());
		} else {
			angle = cardinalOf_2(e.orientation());
		}
		super.rotate(angle);
	}

	public int cardinalOf_2(int angle) {
		
			if (angle >= 0 && angle < 90){
				return 90;
			}else if (angle >= 90 && angle < 180) {
				return 180;
			}else if(angle >= 180 && angle < 270) {
				return 270;
			}else {
				return 0;
			}
	}
			
	public int cardinalOf_1(int angle) {
			if(angle > 0 && angle <=90) {
				return 0;
			}else if(angle > 90 && angle <=180) {
				return 90;
			}else if (angle > 180 && angle <= 270) {
				return 180;
			}else {
				return 270;
			}
			
		}
		

	

}
