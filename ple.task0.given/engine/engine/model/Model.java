package engine.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import engine.view.View;

public class Model {
  private int m_ncols, m_nrows;
  private Entity[][] m_grid;
  private Player m_player;
  private List<Entity> m_entities;
  private View m_view;
  private Config m_conf;

  public Model(int nr, int nc) {
    m_ncols = nc;
    m_nrows = nr;
    m_grid = new Entity[nr][nc];
    m_entities = new LinkedList<Entity>();
  }

  /* 
   * A callback from the constructor of an entity
   * to notify this model of a new entity to add
   * to this model.
   */
  void addAt(Entity e) {
    throw new RuntimeException("NYI");
  }

  /*
   * Move the given entity from its current location
   * by adding the given number of rows and columns
   * to its current location.
   */
  void move(Entity e, int nrows, int ncols) {
    throw new RuntimeException("NYI");
  }

  /* 
   * Normalize a number back to the range [0,lengh[
   */
  private int normalize(int n, int length) {
    throw new RuntimeException("NYI");
  }

  public Entity entity(int r, int c) {
    throw new RuntimeException("NYI");
  }

  public Iterator<Entity> entities() {
    return m_entities.iterator();
  }

  public Config config() {
    return m_conf;
  }
  
  public void config(Config c) {
    m_conf = c;
  }

  public Player player() {
    return m_player;
  }

  public int ncols() {
    return m_ncols;
  }

  public int nrows() {
    return m_nrows;
  }

}
