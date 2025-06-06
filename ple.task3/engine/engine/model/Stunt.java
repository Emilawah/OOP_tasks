package engine.model;

import engine.IBrain.IBot;
import oop.tasks.Task;

public abstract class Stunt {

	public interface Action {
		void tick(int elapsed);
	}

	protected Model m;
	protected Entity e;
	protected Action action;
	protected int progress;

	protected Stunt(Model m, Entity e) {
		this.e = e;
		this.m = m;
		e.stunt = this;
	}

	// ACTIONS
	public boolean move(int nrows, int ncols) {
		return m.move(e, nrows, ncols);
	}

	public void rotate(int angle) {
		e.face(angle);
	}

//	public void tick(int elapsed) {
//		if (action == null) {
//			if (e.bot != null) {
//				e.bot.think(elapsed);
//			}
//		} else {
//			action.tick(elapsed);
//		}
//	}

//	public int progress() {
//		return progress;
//	}

//	private class Motion implements Action {
//
//		private int delay;
//		private int step;
//		private int duration;
//		private int elapsed;
//		private boolean moved;
//		private int nrows;
//		private int ncols;
//
//		Motion(int nrows, int ncols, int duration) {
//			this.delay = duration / 2; // half-duration
//			this.duration = duration;
//		}
//
//		@Override
//		public void tick(int elapsed) {
//			float percent;
//
//			this.elapsed += elapsed;
//			percent = (float) this.elapsed / (float) duration;
//			Stunt.this.progress = (int) (100 * percent);
//
//			delay -= elapsed;
//			if (delay > 0)
//				return;
//
//			switch (step) {
//			case 0:
//				step++;
//				moved = m.move(e, nrows, ncols);
//				this.delay = duration / 2;
//				break;
//			case 1:
//				step++;
//				action = null;
//				break;
//			}
//
//		}
//
//	}

}
