package game;

import java.awt.Graphics2D;

import engine.IController;
import engine.IModel;
import engine.IModel.Config;
import engine.IView;
import engine.controller.Controller;
import engine.model.Model;
import engine.model.Player;
import engine.view.View;
import oop.graphics.Canvas;

public class Game {
	private Canvas m_canvas;
	private Model m_model;
	private View m_view;
	private Controller m_controller;

	Game(Canvas canvas, int nrows, int ncols) {
		this.m_canvas = canvas;

		IModel.Config conf = new Config();
		conf.tore = true;
		;

		m_model = new Model(nrows, ncols);
		m_model.config(conf); // configure before adding entities

		new Player(m_model, 5, 5, 90);

		m_view = new View0(canvas, m_model);
		m_controller = new Controller0(canvas, m_model, m_view);
		m_model.setView(m_view);

	}

	public void paint(Canvas canvas, Graphics2D g) {
		m_view.paint(canvas, g);
	}

	public void tick(int elapsed) {
		// TODO Auto-generated method stub

	}

}
