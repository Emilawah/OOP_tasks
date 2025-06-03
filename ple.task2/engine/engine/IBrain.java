package engine;

import engine.model.Entity;

public interface IBrain {
	interface IBot{
		Entity entity();
		void think();
	}
}
