package game.bot.tracker;

import engine.model.Entity;

import engine.model.Model;
import engine.model.Stunt;
import engine.model.Stunt.Action;
import game.bot.walker.StuntWalker;

public class TrackerStunt extends Stunt{

	public TrackerStunt(Model m, Entity e) {
		super(m, e);
	}

	public boolean move(int nrows, int ncols) {
		if (action == null) {
			this.action = new TrackerMotion(this, nrows, ncols, 500);
			return true;
		}
		return false;
	}

	public int getSTX() {
		if (action instanceof TrackerMotion) {
			return ((TrackerMotion) action).getNCols();
		}
		return 0;
	}

	public int getSTY() {
		if (action instanceof TrackerMotion) {
			return ((TrackerMotion) action).getNRows();
		}
		return 0;
	}

	private class TrackerMotion implements Action {

		private  TrackerStunt st;
		private  Model m;
		private  Entity e;

		private int nrows, ncols;
		private int duration;
		private int delay, step, elapsed;
		private boolean moved;

		public TrackerMotion(TrackerStunt st, int nrows, int ncols, int duration) {
			this.st = st;
			this.m = st.m;
			this.e = st.e;
			this.nrows = nrows;
			this.ncols = ncols;
			this.duration = duration;
			this.delay = duration / 2;
			this.step = 0;
			this.elapsed = 0;
		}

		@Override
		public void tick(int elapsed) {
			this.elapsed += elapsed;
			float percent = (float) this.elapsed / duration;
			st.progress = (int) (100 * percent);

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
			return 0;
		}
		
		public int getNRows() {
			return nrows;
		}

		public int getNCols() {
			return ncols;
		}

	}
}
