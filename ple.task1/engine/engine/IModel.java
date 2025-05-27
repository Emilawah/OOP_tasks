package engine;

import java.util.Iterator;

import engine.model.Entity;
import engine.model.Player;

public interface IModel {
	int ncols();

	int nrows();

	Player player();

	Iterator<Entity> entities();

	Entity entity(int r, int c);

	public class Config {
		public boolean tore;
	}

	Config config();

	void config(Config c);
}
