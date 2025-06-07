package game.player;

import engine.model.Entity;
import engine.model.Model;
import engine.model.Player;
import engine.model.Stunt;

public class StuntPlayer extends Stunt {

	protected float speed_meter = 1; // 1 déplacement -> vaut le nombre de mètres parcouru
	private Player p;

	public StuntPlayer(Model m, Entity e) {
		super(m, e);
		this.p = (Player) e;
	}

	void progress(int progress) {
		this.progress = progress;
	}

	Player entity() {
		return p;
	}

	Model model() {
		return m;
	}

	public boolean move(int nrows, int ncols) {
		if (action == null) {
			this.action = new PlayerMotion(this, nrows, ncols, 500);
			return true;
		}
		return false;
	}

	public void up() {
		e.face(0);
		move(-1, 0);
	}

	/*
	 * Move this entity down one row.
	 */
	public void down() {
		e.face(180);
		move(1, 0);
	}

	/*
	 * Move this entity left one column.
	 */
	public void left() {
		e.face(270);
		move(0, -1);
	}

	/*
	 * Move this entity right one column.
	 */
	public void right() {
		e.face(90);
		move(0, 1);
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

		if (angle >= 0 && angle < 90) {
			return 90;
		} else if (angle >= 90 && angle < 180) {
			return 180;
		} else if (angle >= 180 && angle < 270) {
			return 270;
		} else {
			return 0;
		}
	}

	public int cardinalOf_1(int angle) {
		if (angle > 0 && angle <= 90) {
			return 0;
		} else if (angle > 90 && angle <= 180) {
			return 90;
		} else if (angle > 180 && angle <= 270) {
			return 180;
		} else {
			return 270;
		}

	}

	public class PlayerMotion implements Action {

		private StuntPlayer sp;
		private Model m;
		private Player p;

		private int nrows, ncols;
		private int duration, delay, step, elapsed;
		private boolean moved;

		PlayerMotion(StuntPlayer sp, int nrows, int ncols, int duration) {
			this.sp = sp;
			this.nrows = nrows;
			this.ncols = ncols;
			this.duration = duration;
			this.delay = duration / 2;
			this.step = 0;
			this.elapsed = 0;

			this.m = sp.model();
			this.p = sp.entity();


		}

		@Override
		public void tick(int elapsed) {

			this.elapsed += elapsed;

			float percent;

			percent = (float) this.elapsed / (float) duration;
			sp.progress = (int) (100 * percent);

			delay -= elapsed;
			if (delay > 0)
				return;

			switch (step) {
			case 0:
				step++;
				moved = m.move(e, nrows, ncols);
				this.delay = duration / 2;
				break;
			case 1:
				step++;
				action = null;
				break;
			}

		}

		@Override
		public int kind() {
			// TODO Auto-generated method stub
			return 0;
		}

		public boolean hasMoved() {
			return moved;
		}

		public int getNRows() {
			return nrows;
		}

		public int getNCols() {
			return ncols;
		}

	}

}
