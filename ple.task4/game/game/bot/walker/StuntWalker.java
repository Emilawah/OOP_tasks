package game.bot.walker;

import engine.model.Entity;

import engine.model.Model;
import engine.model.Stunt;

public class StuntWalker extends Stunt {

	public StuntWalker(Model m, Entity e) {
		super(m, e);
	}

	public boolean move(int nrows, int ncols) {
		if (action == null) {
			this.action = new WalkerMotion(this, nrows, ncols, 300);
			return true;
		}
		return false;
	}

	public int getSWX() {
		if (action instanceof WalkerMotion) {
			return ((WalkerMotion) action).getNCols();
		}
		return 0;
	}

	public int getSWY() {
		if (action instanceof WalkerMotion) {
			return ((WalkerMotion) action).getNRows();
		}
		return 0;
	}

	private class WalkerMotion implements Action {

		private final StuntWalker sw;
		private final Model m;
		private final Entity e;

		private final int nrows, ncols;
		private final int duration;
		private int delay, step, elapsed;
		private boolean moved;

		public WalkerMotion(StuntWalker sw, int nrows, int ncols, int duration) {
			this.sw = sw;
			this.m = sw.m;
			this.e = sw.e;
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
			sw.progress = (int) (100 * percent);

			delay -= elapsed;
			if (delay > 0)
				return;

			switch (step) {
			case 0:
				step++;
				moved = m.move(e, nrows, ncols);
				this.delay = duration / 20;
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
