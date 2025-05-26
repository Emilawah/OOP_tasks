package engine;

import oop.graphics.Canvas;
import oop.graphics.Graphics;
import oop.tasks.Task;

public class Painter implements Runnable {
  private Game m_game;
  private Canvas m_canvas;
  private int m_ncols, m_nrows;

  Painter(Canvas canvas, int nr, int nc) {
    m_nrows = nr;
    m_ncols = nc;
    m_canvas = canvas;
    throw new RuntimeException("NYI");
  }

  @Override
  public void run() {
    throw new RuntimeException("NYI");
  }

  class PaintListener implements Canvas.PaintListener {

    @Override
    public void paint(Canvas canvas, Graphics g) {
      throw new RuntimeException("NYI");
    }

    @Override
    public void visible(Canvas canvas) {
      throw new RuntimeException("NYI");
    }

    @Override
    public void revoked(Canvas canvas) {
      System.exit(0);
    }

  }

}
