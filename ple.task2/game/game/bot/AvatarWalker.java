package game.bot;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.model.Entity;
import engine.view.Avatar;
import engine.view.View;

public class AvatarWalker extends Avatar{

	public AvatarWalker(View v, Entity e) {
		super(v, e);
	}

	
	public void render(Graphics2D g) {
	    int w = canvas.getWidth();
	    int h = canvas.getHeight();
	    int nrows = model.nrows();
	    int ncols = model.ncols();
	    int cell = Math.min(w / ncols, h / nrows);

	    int l = (int) (cell * 0.6);       // largeur du triangle
	    int hauteur = (int) (cell * 0.7); // hauteur du triangle

	    // Coordonnées du centre de la case de l'entité
	    int x = e.col() * cell + cell / 2;
	    int y = e.row() * cell + cell / 2;

	    // Triangle centré autour de (0,0)
	    Polygon triangle = new Polygon();
	    triangle.addPoint(0, -hauteur / 2);      
	    triangle.addPoint(-l / 2, hauteur / 2);   
	    triangle.addPoint(l / 2, hauteur / 2);    

	    // Déplacer le triangle à la bonne position
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setColor(Color.BLUE);
	    g2.translate(x, y); // translation
	    g2.fillPolygon(triangle);
	    g2.dispose();
	}
}
