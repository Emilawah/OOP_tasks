package game.player;

import engine.model.Entity;
import engine.model.Model;
import engine.model.Stunt;

public class StuntPlayer extends Stunt{

	protected float speed_meter = 1; // 1 déplacement -> vaut le nombre de mètres parcouru

	
	public StuntPlayer(Model m, Entity e) {
		super(m, e);
		
	}

	public void up() {
		m.moveM(0, -speed_meter);
	}

	/*
	 * Move this entity down one row.
	 */
	public void down() {
		m.moveM(0, speed_meter);
	}

	/*
	 * Move this entity left one column.
	 */
	public void left() {
		m.moveM(-speed_meter, 0);
	}

	/*
	 * Move this entity right one column.
	 */
	public void right() {
		m.moveM(speed_meter, 0);
	}

	@Override
	public void rotate(int angle) {
		angle = cardinalOf(angle);
		super.rotate(angle);
	}
	
	public int cardinalOf(int angle) {
		if((angle >= 315 && angle < 360) || (angle >=0 && angle <45)) {
	    	return 0;
	    }else if(angle >= 45 && angle < 135) {
	    	return 90;
	    }else if(angle >= 135 && angle < 225) {
	    	return 180;
	    }else {
	    	return 270;
	    }
	}

}
