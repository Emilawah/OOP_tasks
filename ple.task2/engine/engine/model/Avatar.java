package engine.model;

import java.awt.Graphics2D;

import engine.view.View;

public abstract class Avatar{
	View v;
	Entity e;

	Avatar(View v, Entity e) {
		this.v = v;
		this.e = e;
		e.avatar = this;
	}

	abstract void render(Graphics2D g);
}
