package engine.view;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.model.Model;
import engine.model.Player;
import oop.graphics.Canvas;

public class View {

  private Canvas m_canvas;
  private Model m_model;

  public View(Canvas canvas, Model model) {
    m_canvas = canvas;
    m_model = model;
  }

  public void focus(int px, int py) {
    throw new RuntimeException("NYI");
  }

  public void paint(Canvas canvas, Graphics2D g) {
    
    // paint the grid with black lines 
    // and grey background.
    
    // paint the string "Hello", with yellow ink 
    // on a blue background, where the left-lower corner
    // of the blue background is at the mouse position
    
    // then paint the player's entity.
    
    throw new RuntimeException("NYI");
  }
  
  private void paintPlayer(Graphics2D g, Player p, int x, int y, Polygon pg) {
    int d = p.orientation();
    double rot = Math.toRadians(d);
    AffineTransform saved = g.getTransform();
    g.translate(x, y);
    g.rotate(rot);
    g.fillPolygon(pg);
    g.setTransform(saved);
  }

}
