package engine.view;

import java.awt.Graphics2D;

import engine.IModel;
import engine.model.Entity;
import oop.graphics.Canvas;

public abstract class Avatar{
	protected Canvas canvas;
	protected IModel model;
	protected View v;
	protected Entity e;

	public Avatar(View v, Entity e) {
		this.canvas = v.m_canvas;
		this.model = v.m_model;
		this.v = v;
		this.e = e;
		e.avatar = this;
	}

	public abstract void render(Graphics2D g);
}
