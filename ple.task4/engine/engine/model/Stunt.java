package engine.model;

import engine.IBrain.IBot;

public abstract class Stunt {
	protected Model m;
	protected Entity e;
	protected Action action;
	protected int progress;
	private static final int DURATION = 1;

	public interface Action {

		int kind();

		void tick(int elapsed);
	}

	protected Stunt(Model m, Entity e) {
		this.e = e;
		this.m = m;
		e.stunt = this;
	}

	// ACTIONS

	public boolean moved() {
		return action != null;
	}
	
	
	public abstract boolean move(int nrows, int ncols);

	public void rotate(int angle) {
		e.face(angle);
	}

	public int progress() {
		return progress;
	}

	public Action action() {
		return action;
	}

	public void tick(int elapsed) {
		if (action == null) {
	        if (e.bot != null) {
	            e.bot.think(elapsed);
	        }
	        // sinon : ne rien faire (pas d’action, pas de bot à appeler)
	    } else {
	        action.tick(elapsed);
	    }
	}

	public class Motion implements Action {

		private int nrows, ncols;
		private int delay, duration, step, elapsed;
		private boolean moved;


		public Motion(int nrows, int ncols, int duration) {
			this.nrows = nrows;
			this.ncols = ncols;
			this.duration = duration;
			this.delay = duration / 2;
			this.elapsed = 0;
		
		}

		@Override
		public void tick(int elapsed) {
			float percent;

			this.elapsed += elapsed;
			percent = (float) this.elapsed / (float) duration;
			
			Stunt.this.progress = (int) (100 * percent);

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
	}

}