package engine;

import java.util.Iterator;

import engine.model.Entity;
import engine.model.Player;
import engine.view.View;

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
	
	public float getDim();
	public void moveM(float x, float y);
	void setView(View v);
	
	public void register(IView v); // le modèle informe à la vue que une entité a été créer
	public void unregister(IView v);//le modèle informe à la vue que une entité a été détruite
}