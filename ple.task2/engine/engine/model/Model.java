package engine.model;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;

import engine.IModel;
import engine.IView;
import engine.view.View;

public class Model implements IModel {
	private int m_ncols, m_nrows;
	private Entity[][] m_grid;
	private Player m_player;
	private List<Entity> m_entities;
	private IView m_view;
	private Config m_conf;
	private float dimCell = 4;

	public Model(int nr, int nc) {
		m_ncols = nc;
		m_nrows = nr;
		m_grid = new Entity[nr][nc];
		m_entities = new LinkedList<Entity>();
	}

	/*
	 * A callback from the constructor of an entity to notify this model of a new
	 * entity to add to this model.
	 */
	void addAt(Entity e) {
		m_entities.add(e);
		if (e.m_row >= 0 && e.m_row < m_nrows && e.m_col >= 0 && e.m_col < m_ncols) {
			m_grid[e.m_row][e.m_col] = e;
		}

		if (e instanceof Player && m_player == null) {
			m_player = (Player) e;
		}
	}

	/*
	 * Move the given entity from its current location by adding the given number of
	 * rows and columns to its current location.
	 */
	public void move(Entity e, int nrows, int ncols) {

		int new_row = e.m_row+nrows;
		int new_col = e.m_col+ncols;

		if(m_conf.tore) {
			new_row = normalize(new_row, nrows());
			new_col = normalize(new_col, ncols());
		} 
		else {
			if(new_row >= m_nrows || new_col >= m_ncols || new_row < 0 || new_col <0 ) {
				new_row = e.m_row;
				new_col = e.m_col;
			}
		}
		
		if(entity(new_row,new_col) == null) {
			m_grid[e.m_row][e.m_col] = null;
			e.m_row = new_row;
			e.m_col = new_col;
			m_grid[new_row][new_col] = e;
		}
		
	}

	public void moveM(float x, float y) {

		// On déplace le joueur
		float px = m_player.px + x;
		float py = m_player.py + y;

		if (m_conf.tore) {
			// normaliser en mètres
			px = normalize(px, m_ncols * dimCell);
			py = normalize(py, m_nrows * dimCell);
		} else {

			if (px >= m_ncols * dimCell || py >= m_nrows * dimCell || py < 0 || px < 0) {
				// si les nouvelles coordonées dépassent la taille du "terrain", il retourne au
				// point de départ
				px = m_player.px;
				py = m_player.py;
			}
		}
		int newRow = (int) (py / dimCell);
		int newCol = (int) (px / dimCell);

		// verifies si le player a bougé de case ou non
		if (newRow != m_player.row() || newCol != m_player.col()) {
			if (m_grid[newRow][newCol] != null)
				return;
		}
		// on passe l'ancienne case du joueur à null
		m_grid[m_player.m_row][m_player.m_col] = null;

		m_player.m_row = newRow;
		m_player.m_col = newCol;
		m_player.px = px;
		m_player.py = py;

		// on met la position du joueur dans la nouvelle case de la grille
		m_grid[newRow][newCol] = m_player;
	}

	/*
	 * Normalize a number back to the range [0,lengh[
	 */
	private int normalize(int n, int length) {
		while (n >= length) {
			n = n - length;
		}
		while (n < 0) {
			n = n + length;
		}
		return n;
	}

	private float normalize(float n, float length) {
		while (n >= length) {
			n = n - length;
		}
		while (n < 0) {
			n = n + length;
		}
		return n;
	}

	@Override
	public Entity entity(int r, int c) {
		return m_grid[r][c];
	}

	@Override
	public Iterator<Entity> entities() {
		return m_entities.iterator();
	}

	public Config config() {
		return m_conf;
	}

	public void config(Config c) {
		m_conf = c;
	}

	@Override
	public Player player() {
		return m_player;
	}

	@Override
	public int ncols() {
		return m_ncols;
	}

	@Override
	public int nrows() {
		return m_nrows;
	}

	@Override
	public void setView(View v) {
		this.m_view = v;
	}

	public float getDim() {
		return dimCell;
	}


	
	@Override
	public void register(IView v) {
		
	}

	@Override
	public void unregister(IView v) {
		
	}

}
