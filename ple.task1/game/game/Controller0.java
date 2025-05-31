package game;

import engine.IModel;

import engine.IView;
import engine.controller.Controller;
import engine.utils.Utils;
import oop.graphics.Canvas;

public class Controller0 extends Controller {

	private int mouseX;
	private int mouseY;

	public Controller0(Canvas canvas, IModel model, IView view) {
		super(canvas, model, view);
	}

	@Override
	protected void pressed(Canvas canvas, int keyCode, char keyChar) {
		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT)
				&& m_shift) {
			m_model.player().rotate(-90);
		}
		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT)
				&& m_shift) {
			m_model.player().rotate(90);
		}

		if ((keyCode == oop.graphics.VirtualKeyCodes.VK_LEFT || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_LEFT)
				&& !m_shift && !m_control) {
			m_model.player().left();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_RIGHT
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_RIGHT) && !m_shift && !m_control) {
			m_model.player().right();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_UP || keyCode == oop.graphics.VirtualKeyCodes.VK_KP_UP)
				&& !m_control) {
			m_model.player().up();
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_DOWN
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_DOWN) && !m_control) {
			m_model.player().down();
		}

	}

	@Override
	protected void released(Canvas canvas, int keyCode, char keyChar) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void typed(Canvas canvas, char keyChar) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void pressed(Canvas canvas, int bno, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void released(Canvas canvas, int bno, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void moved(Canvas canvas, int px, int py) {

		// en pixels
		this.mouseX = px;
		this.mouseY = py;

		m_model.player().face(angle(canvas));

	}

	private int angle(Canvas canvas) {

		// Position (x,y) du joueur (on converti en pixels)
		float x0 = m_model.player().getX() * m_view.getSizeCell() / m_model.getDim();
		float y0 = m_model.player().getY() * m_view.getSizeCell() / m_model.getDim();

		// position (x,y) de la souris
		float x = (mouseX - m_view.getGraphX()) / m_view.getZoom();
		float y = (mouseY - m_view.getGraphY()) / m_view.getZoom();

		// calcul distance entre joueur et le point souris
		float dx = x - x0;
		float dy = y - y0;

		int coord = Utils.theta(-dy, dx);

		return coord;
	}

}
