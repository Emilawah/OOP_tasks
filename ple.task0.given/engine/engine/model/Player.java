package engine.model;

public class Player extends Entity {

  public Player(Model m, int x, int y, int o) {
    super(m, x, y, o);
  }

  /*
   * Move this entity up one row.
   */
  public void up() {
    throw new RuntimeException("NYI");
  }

  /*
   * Move this entity down one row.
   */
  public void down() {
    throw new RuntimeException("NYI");
  }

  /*
   * Move this entity left one column.
   */
  public void left() {
    throw new RuntimeException("NYI");
  }

  /*
   * Move this entity right one column.
   */
  public void right() {
    throw new RuntimeException("NYI");
  }

}
