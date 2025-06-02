package game;

import engine.IModel;

import engine.IView;
import engine.controller.Controller;
import engine.utils.Utils;
import oop.graphics.Canvas;
import oop.tasks.Task;

public class Controller0 extends Controller {

	private int mouseX;
	private int mouseY;
	private Runnable moveTask; // pour le déplacement du joueur
	private Runnable mouseTask;// pour le curseur de la souris (orientation)
	private Runnable mousePressedTask; // pour le boutton de la souris

	private boolean leftRotation; // pour la rotation a droite (button pressed)
	private boolean rightRotation; // pour la rotation a gauche (button pressed)

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
			m_model.player().startMove();
			if (moveTask == null) {

				moveTask = new Runnable() {

					public void run() {
						if (m_model.player().isMoving()) {
							m_model.player().movePlayer();
							Task.task().post(this, 30);
						} else {
							moveTask = null;
						}
					}
				};
				Task.task().post(moveTask);
			}
		} else if ((keyCode == oop.graphics.VirtualKeyCodes.VK_DOWN
				|| keyCode == oop.graphics.VirtualKeyCodes.VK_KP_DOWN) && !m_control) {

			m_model.player().stopMove();

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

		if (bno == 1) {
			leftRotation = true;
		} else if (bno == 3) {
			rightRotation = true;
		}

		if (mousePressedTask == null) {
			mousePressedTask = new Runnable() {
				public void run() {
					if (leftRotation) {
						m_model.player().rotate(-10);
						Task.task().post(this, 30);
					} else if (rightRotation) {
						m_model.player().rotate(10);
						Task.task().post(this, 30);
					} else {
						mousePressedTask = null;
					}
				}
			};
			Task.task().post(mousePressedTask);
		}

	}

	@Override
	protected void released(Canvas canvas, int bno, int x, int y) {
		if (bno == 1) {
			leftRotation = false;
		} else if (bno == 3) {
			rightRotation = false;
		}
	}

	@Override
	protected void moved(Canvas canvas, int px, int py) {

		// en pixels
		this.mouseX = px;
		this.mouseY = py;
		if (!leftRotation && !rightRotation) {
			mouseTask = new Runnable() {
				public void run() {
					m_model.player().face(angle(canvas));
					mouseTask = null;
				}
			};
			Task.task().post(mouseTask, 30);
		}

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
