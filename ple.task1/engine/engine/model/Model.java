package engine.model;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;

import engine.IModel;
import engine.IView;

public class Model implements IModel {
  private int m_ncols, m_nrows;
  private Entity[][] m_grid;
  private Player m_player;
  private List<Entity> m_entities;
  private IView m_view;
  private Config m_conf;

  public Model(int nr, int nc) {
    m_ncols = nc;
    m_nrows = nr;
    m_grid = new Entity[nr][nc];
    m_entities = new LinkedList<Entity>();
    m_player = new Player(this, 0, 0, 90);
    addAt(m_player);
  }

  /* 
   * A callback from the constructor of an entity
   * to notify this model of a new entity to add
   * to this model.
   */
  void addAt(Entity e) {
	  m_entities.add(e);
  }

  /*
   * Move the given entity from its current location
   * by adding the given number of rows and columns
   * to its current location.
   */
  void move(Entity e, int nrows, int ncols) {
	  
	int row_old = e.m_row;
	int col_old = e.m_col;
	
	  
    if(this.m_conf.tore) {
    	e.m_row += nrows;
    	e.m_col += ncols;
    	e.m_row = normalize(e.m_row, nrows());
    	e.m_col = normalize(e.m_col, ncols());
    }
    else {
    	e.m_row += nrows;
    	e.m_col += ncols;
    	if(e.m_row >= nrows() || e.m_col >= ncols() || e.m_row < 0 || e.m_col < 0) {
    		// si les nouvelles coordonées dépassent la taille du "terrain", il retourne au point de départ
    		e.m_row = row_old;
        	e.m_col = col_old;
    	}
    	
    }
  
  }

  /* 
   * Normalize a number back to the range [0,lengh[
   */
  private int normalize(int n, int length) {
	  while(n >= length) {
		  n = n - length;
	  }
	  while(n < 0) {
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

}
