package engine;

import engine.model.Entity;

public interface IBrain {
	
	public interface IBot{
		Entity entity();
		void think(int elapsed);
	}
}
